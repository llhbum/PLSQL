public class Chapter17_저장함수 {
    /*
    Chapter17_저장함수
        [개념]
            PL/SQL 저장 함수는  RETURN문을 사용하여 하나의 값을 반환하는 서브프로그램으로,
            고유한 이름을 가지고, 데이터베이스에 저장되며, 이름을 식별자로 사용하여 반복적으로 재사용될 수 있다.
            저장 함수는 입력 값으로 매개변수를 가질 수 있으며, 일반적으로 서로 다른 입력 값에 따라서 서로 다른 결괏값을 반환한다.

    * 17.1 함수의 기본 구조
        [기본구조]
            Create [or REPLCACE ] FUCTION 함수명 [(매개변수목록)]
            RETURN 반환데이터타입
            IS -- IS 대신 AS를 사용해도 동일함
                선언부 -- 타입이나 상수, 변수, 커서, 예외, 중첩된 서브프로그램 등 선언하는 곳
            BEGIN
                실행부
                RETURN 반환값
            EXCEPTION
                예외처리부
            END;

        [예제]
            CREATE OR REPLACE FUNCTION get_wage(a_empno NUMBER)
                RETURN NUMBER
            -- 사원의 급여와 커미션의 합을 반환하는 함수
            IS -- 선언부 시작, IS 대신 AS 사용 가능
                v_wage NUMBEr;
            BEGIN -- 실행부 시작
            -- 사번이 a_empno인 사원의 급여와 커미션의 합을 조회한다.
                SELECT sal + NVL(comm, 0) comm
                    INTO v_wage
                FROM emp
                WEHRE empno = a_empno;
                --급여를 반환한다.
                RETURN v_wage;
                EXCEPTION -- 예외 처리부 시작
                    WHEN NO_DATA_FOUND THEN
                    --사원이 존재하지 않을 경우는 -1을 반환한다.
                RETURN -1;
            END;

    * 17.2 함수의 매개변수
        대부분의 함수는 매개변수를 가진다.
            [구조]
                ( 매개변수명 데이터타입 [, 매개변수명 데이터타입 ... ] )

            * 매개변수명이 없는데 매개변수명() 를 하면 컴파일 오류가 발생한다.

    * 17.3 함수의 선언부
        [개념]
            함수의 선언부에는 타입, 상수, 변수, 커서, 예외, 서브프로그램 전방 선언, 서브프로그램 정의가 올 수 있다.
            물론 필요하지 않은 것들은 생략할 수 있다.
            각 종류별 배치 순서에서는 *서브프로그램 정의*가 가장 나중에 와야 한다.
        [예제]
            create or replace function f return number
            is
            -- 선언부 시작
            function get_emp_sal(a_empno number) return number; -- 함수 전방 선언

            no_emp_found exception; -- 사용자 지정 예외 선언

            type number_arr_type is table of number; -- 타입선언
            v_empno_arr number_arr_type; -- 변수 선언

            c_nulm_comm constant number := 0; -- 상수선언
            v_wage number; -- 변수선언

            cursor emp_cursor(a_empno number) is -- 커서 선언
                select sal + nvl(comm, 0) comm
                from emp
                where empno = a_empno;

            function get_emp_sal(a_empno number) return number is -- 함수정의
                v_sal number;
            begin
                open emp_cursor(7788);
                fetch emp_cursor into v_sal;
                close emp_cursor ;
                return v_sal;
            end;
            -- 함수 선언부 끝
            begin
                null;
            end;


    * 17.4 함수의 반환값
        [개념]
            함수는 반드시 하나의 값을 반환해야 한다. 값의 반환에는 RETURN문을 사용하는데, RETURN문을 만나면 값을 반환하면서 함수를 즉시 종료하고 호출한 프로그램으로 돌아간다.
            반환하는 값은 단일값이다. 예를 들어 점의 좌표(x,y)를 반환하고자 한다면 두 값을 필드로 가지는 레코드 변수를 선언한 후 이 타입의 레코드를 반환해야지, 좌표를 나타내는
            두 개의 값 x와 y를 별도의 두 개 값으로 반환할 수는 없다.

        [예제]
            1. 함수에서 컬렉션 반환을 위한 객체 타입 선언
                CREATE OR REPLCAE TYPE empno_arr_type IS TABLE OF NUMBER;

            2. 컬렉션을 반환하는 함수 정의
                CREATE OR REPLACE FUNCTION get_emp_list(a_deptno NUMBER)
                    return empno_arr_type
                IS
                    v_empno_arr empno_arr_type;
                BEGIN
                    SELECT empno
                    BULK COLLECT INTO v_empno_arr
                    FROM emp
                    WHERE deptno = a_deptno
                    ORDER BY empno;
                    RETURN v_empno_arr;
                END;

            3. 레코드를 반환하는 함수 정의
                CREATE OR REPLACE FUNCTION get_emp_rec(a_empno NUMBER)
                    RETURN emp%ROWTYPE
                IS
                    v_emp_rec emp%ROWTYPE;
                BEGIN
                    SELECT *
                        INTO v_emp_rec
                    FROM emp
                    WHERE empno = a_empno;
                    RETURN v_emp_rec;
                END;

            4. 컬렉션 반환 함수에 연속적으로 두 개의 괄호 사용
                BEGIN
                    DBMS_OUTPUT.PUT_LINE(get_emp_list(10)(1));
                END;

            5. 컬렉션 반환 함수가 매개변수를 가지지 않는 경우
                DECLARE
                    -- 매개변수를 가지지 않는 함수
                FUNCTION get_emp_list_of_dept_10 RETURN empno_arr_type
                IS
                    v_empno_arr empno_arr_type;
                BEGIN
                    SELECT empno
                    BULK COLLECT INTO v_empno_arr
                    FROM emp
                    WHERE deptno = 10; -- 부서번호 10의 사원 번호목록
                    RETURN v_empno_arr
                END;
                BEGIN
                    DBMS_OUTPUT.PUT_LINE(get_emp_list_of_dept_10()(1));
                END;

            6. 두 개의 RETURN문을 가지는 함수
                CREATE OR REPLACE FUNCTION max_number(a_1 number, a_2 number)
                    RETURN NUMBER
                IS
                BEGIN
                    IF a_1 <= a_2 THEN
                        RETURN a_2;
                    ELSE
                        RETURN a_1;
                    END IF;
                END;

    * 17.5 저장함수의 사용
        16.4 참조

    * 17.6 저장 함수 사용의 제약 사항
        1. SELECT문에서 호출되는 사용자 정의 함수는 매개변수로 IN 모드만 사용할 수 있다.
           OUT이나 IN OUT 모드는 실행시 오류를 발생시킨다.

        2. 사용자 정의 함수가 SELECT 문에서 호출된 경우에는 DML을 사용할 수 없다.

        3. 사용자 정의 함수 내에서는 트랜잭션 제어를 할 수 없다.

        4. 사용자 정의 함수내에서는 DDL을 실행할 수 없다.

        5. CREATE TABLE문이나 ALTER TABLE문의 check나 DEFAULT절에는 사용자 정의 함수를 사용할 수 없다.
     */
}
