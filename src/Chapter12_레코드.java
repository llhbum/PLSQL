public class Chapter12_레코드 {
    /*
    Chapter12_레코드
        [개념]
            - 레코드를 사용하면 데이터타입이나 길이가 다른 여러 변수들을 논리적으로 하나의 그룹으로 묶을 수 있다 := 구조체

        12.1 레코드 사용
            [구조]
                TYPE 타입명 IS RECORD (필드목록) ;
                레코드변수명 타입명;

        12.2 레코드를 SQL에 사용하기

            [예제]
                - SELECT문에 레코드 사용
                    DECLARE
                        TYPE emp_type IS RECORD(
                            emp NUMBER(4) NOT NULL := 0, -- NOT NULL 필드는 반드시 초깃값을 지정해야 함
                            ename emp.ename%TYPE, -- 칼럼 앵커를 사용한 필드 선언
                            job VARCHAR2(9) -- 필드의 데이터 타입을 직접 지정
                        );
                        v_emp emp_type ;
                    BEGIN
                        -- INTO절에 세 개의 필드를 지정하는 대신 레코드 변수를 지정할 수 있다.
                        SELECT empno, ename, job
                            INTO v_emp -- INTO v_emp.empno, v_emp.ename, v_emp.job -- 세 개의 변수를 윗줄의 레코드 하나로 대체
                        FROM emp
                        WHERE empno = 7788;

                        DBMS_OUTPUT.PUT_LINE('EMPNO = ' || v_emp.empno);
                        DBMS_OUTPUT.PUT_LINE('ENAME = ' || v_emp.ename);
                        DBMS_OUTPUT.PUT_LINE('JOB = ' || v_emp.job);
                    END;

                -- 12-4 레코드의 필드명과 SELECT되는 테이블의 칼럼명과는 일치하지 않아도 된다.
                -- 12-5 레코드 변수는 하나만 사용 가능하며, 다른 변수와 같이 사용할 수 없다.

        12.3 레코드 변수에 값 할당
            `[예제]
                - 예제 12-6 레코드 변수의 각 필드에 값을 할당하는 방법
                - 예제 12-7 필드가 동일하더라도 타입명이 다른 레코드 간에는 할당 연산이 불가능 하다.
                - 예제 12-8 레코드의 모든 필드를 한 번에 초기화하기 위해 사용자 정의 함수를 사용할 수 있다.

        12.4 레코드와 컬렉션의 혼합
            [개념]
                - 둘을 동시에 사용한다는 것은 레코드 컬렉션(레코드 배열)이나 컬렉션 레코드(레코드의 필드가 컬렉션) 또는 레코드의 필드로 레코드를 사용할 수 있다는 것을 뜻한다.
            [예제]
                - 12-9 레코드와 컬렉션 동시 사용
                DECLARE
                    TYPE city_tab_type IS TABLE OF VARCHAR2(64 INDEX BY PLS_INTEGER;
                    TYPE name_rec IS RECORD (
                        first_name VARCHAR2(30),
                        last_name VARCHAR2(30)
                        );
                    TYPE emp_rec IS RECORD (
                        empno emp.empno%TYPE DEFAULT 1000,
                        ename name_rec, -- 레코드가 레코드의 필드가 될 수 있다.
                        city city_tab_type -- 컬렉션이 레코드의 필드가 될 수 있다.
                    );
                    TYPE people_type IS VARRAY(10) OF name_rec ; -- 레코드의 컬렉션이 가능하다.
                    TYPE emp_type IS VARRAY(10) OF emp_rec; -- 레코드의 컬렉션
                BEGIN
                    null;
                END;

        12.5 레코드를 SELECT, INSERT, UPDATE문에 사용할 때의 제약사항
            [종류]
                - 레코드 변수를 사용할 수 있는 곳은 다음뿐이다.
                    - SELECT문의 INTO절
                    - UPDATE문의 SET ROW절에서 =의 오른쪽
                    - INSERT문의 VALUES절
                    - RETURNING절의 INTO 서브절

               - 레코드는 SELECT 리스트나 WHERE절이나 GROUP BY절이나 ORDER BY절에서는 사용할 수 없다.
               - UPDATE문의 SET절에서 ROW는 왼쪽에만 사용할 수 있으며, 'SET ROW = ' 다음에 레코드가 아닌 서브쿼리는 사용할 수 없다.
               - SELECT문의 INTO절, INSERT문의 VALUES절, UPDATE문의 SET ROW절, RETURNING절의 INTO 서브절에 레코드 변수가 사용되는 경우에는
                 단 하나의 레코드 변수만 사용할 수 있으며, 다른 레코드 변수나 스칼라 타입 변수, 리터럴을 같이 사용할 수 없다.
               - 다음은 SELECT, INSERT, UPDATE문에서 지원되지 않는다.
                    - 중첩된 레코드 타입
                    - 레코드 타입을 반환하는 함수
                    - EXECUTE IMMEDIATE문을 사용하여 레코드를 INSERT하거나 UPDATE하기

     */
}
