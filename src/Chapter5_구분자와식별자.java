public class Chapter5_구분자와식별자 {
    /*
    Chapter5_구분자와식별자

    5.1 구분자
        구분자 목록
        := (대입연산자)
        => (저장 서브프로그램의 매개변수명을 값과 연관시키는 연산자)
        % (속성지시자) - 커서 상태 변수에 사용 또는 앵커 타입 지시자
        || (연결연산자)
        .. (범위연산자)
        <>, !=, ~=, ^= (부등 관계 연산자)
        @ (원격 접근 지시자(DB LINK에 사용))

    5.2 식별자
        - 식별자는 PL/SQL 프로그램 구성 요소의 이름을 의미한다.
        식별자 목록
        커서 : DB에 저장된 값을 접근하기 위한 구조
        레이블 : PL/SQL의 블록 또는 문장에 대한 이름
        서브프로그램 : 호출 가능한 함수와 프로시저
        트리거 : 특정 이벤트 발생 시 데이터베이스에 의해 자동적으로 호출되는 저장 서브프로그램
        패키지 : 타입, 상수, 변수, 예외, 커서, 서브프로그램 등을 모듈화한 저장 서브프로그램
        객체 타입 : 관계형 DB에 객체 지향 프로그래밍을 가능하게 하는 프로그램 요소
        예약어 : 내부적으로 사용하기 위해 예약된 단어
        키워드 : 오라클 PL/SQL 문법에서 사용되는 핵심 단어

    5.3 식별자의 유효 범위
        - 모든 식별자는 유효 범위를 가진다. 하나의 PL/SQL 블록 내에서 선언된 식별자는 해당 블록 내에서만 유효하며, 자신의 유효 범위를 벗어나면 인식이 불가능하다.
        [예시]
        DECLARE
            c_table_name CONSTANT STRING(30) := 'EMP" ;
        BEGIN
            DBMS_OUTPUT.PUT_LINE('테이블' || c_table_name || '의 파티션 개수 출력');
            DECLARE -- 중첩된 블록
                v_num_partitions NUMBER;
            BEGIN
                SELECT COUNT(*)
                    INTO v_num_partitions
                FROM USER_TAB_PARTITIONS
                WHERE TABLE_NAME = c_table_name
                DBMS_OUTPUT.PUT_LINE('파티션 개수 = ' || v_num_partitions); -- *에러 : 유효범위를 벗어나서 사용됨 *
            END;
        END;

        - 식별자명 충돌 시의 유효 범위
        [예시]
        DECLARE -- 메인블록
            v_num NUMBER := 0;
        BEGIN
            DECLARE -- 중첩된 블록
                v_num NUMBER ; -- 메인 블록에서와 동일한 이름의 변수 선언
            BEGIN
                v_num := 4; -- 5번 줄에 선언된 변수 v_num 참조
            END
            v_num := v_num + 1; -- 2번 줄에 선언된 v_num을 참조
        END;

        - 레이블을 사용한 식별자 참조
        <<OUTER_BLCOK>> -- 레이블
        DECLARE
            v_num NUMBER := 0;
        BEGIN
            DECLARE
                v_num NUMBER ;
            BEGIN
                v_num := 1;                 -- 6번 줄에 선언된 변수 v_num을 변경시킨다.
                OUTER_BLOCK.v_num := 2;     -- 3번 줄에 선언된 변수 v_num을 변경시킨다.
            END;
        DBMS_OUTPUT.PUT_LINE('v_num = ' || v_num); -- 3번 줄에 선언된 변수 v_num의 값을 출력한다.
        END;

        - 서브프로그램에서의 식별자 유효범위
        CREATE OR REPLACE check_salary(a_empno NUMBER)
        IS
            v_name VARCHAR2(10);
            v_num NUMBER; -- (1)
        FUNCTION check_bonus(a_emp NUMBER) RETURN BOOLEAN
        IS
            v_num NUMBER; -- (2)
        BEGIN
            SELECT comm INTO v_num
            FROM emp
            WHERE empno = a_empno;
        DBMS_OUTPUT.PUT_LINE(v_name || '의 커미션 : ' || v_num); -- (1) v_num 참조

        IF check_salary.v_num < v_num THEN -- (2) v_num 참조
            RETURN FALSE;
        ELSE
            RETURN TRUE;
        END IF;
        END;

        BEGIN
            SELECT ename, sal INTO v_name, v_num
            FROM emp
            WHERE empno = a_empno;

        IF NOT check_bonus(a_empno) THEN
            DBMS_OUTPUT.PUT_LINE('사원' || v_name || '의 커미션이 과도합니다.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('사원 ' || v_name || '의 급여 데이터가 정상입니다.');
        END IF;
        END;
     */
}
