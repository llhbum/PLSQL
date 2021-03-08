public class Chapter13_Cursor {

    /*
    Chapter13_Cursor

    * 커서(Cursor)
        [개념]
            - 클라이언트 메모리에 존재하는 자료구조로써, DB에 저장된 값을 접근하기 위한 구조
            - 우리는 알게 모르게 커서를 사용하여 쿼리를 실행하고 있었다.

        [종류]
            - 묵시적(암시적) 커서
                - DECLARE절에 커서를 선언하지 않은 채로 커서를 사용
                - 커서 선언없음
                - 커서 제어 불가
                - 사용 가능한 SQL 유형 : SELECT, INSERT, UPDATE, DELETE, MERGE
                - 커서 속성 참조 : SQL%커서속성
                - 사용 방법 : 즉시 실행

                [예제]
                    DECLARE
                        v_name emp.ename%TYPE;
                    BEGIN
                        --묵시적 커서
                        SELECT ename INTO v_name
                        FROM emp
                        WHERE empno = 7788;

                        DBMS_OUPUT.PUT_LINE('ENAME = ' || v_name);
                    END;



            - 명시적 커서
                - CURSOR 키워드를 사용하여 커서를 선언한 후에 사용
                - 커서 선언있음
                - 커서 제어 가능
                - 사용 가능한 SQL 유형 : SELECT
                - 커서 속성 참조 : 커서명%커서속성
                - 사용 방법 : OPEN, FETCH, CLOSE

                [예제]
                    - OPEN문을 사용하여 커서를 열고, FETCH문을 사용하여 결과를 추출하고, CLOSE문을 사용하여 커서를 닫는다.
                    DECLARE
                        v_name emp.ename%TYPE;

                        -- 명시적으로 커서를 선언
                        CURSOR ename_cursor IS
                        SELECT ENAME
                        FROM EMP
                        WHERE EMPNO = 7788;
                    BEGIN
                        -- 커서를 OPEN한다.
                        OPEN ename_cursor;

                        -- SELECT 결과를 FETCH한다.
                        FETCH ename_cursor INTO v_name;
                        DBMS_OUPUT.PUT_LINE('ENAME = ' || v_name);

                        -- 커서를 CLOSE한다.
                        CLOSE ename_cursor;
                    END;

                    - LOOP문을 사용하여 여러 건을 FETCH
                    - BULK COLLECT를 사용하여 여러 건을 한 번에 FETCH
                    - 커서에 대한 %ROWTYPE 사용

               [구조]
                    CURSOR 커서명[ (매개변수목록) ] [반환형] IS SELECT문;

        13.2 커서 FOR LOOP
            - 13.2.1 묵시적 커서 FOR LOOP
            - 13.2.2 명시적 커서 FOR LOOP

        13.3 커서 속성
            [개념]
                - 커서 속성은 처리 중인 커서의 상태를 확인할 수 있게 해준다.
            [비교]
                - 명시적 커서 속성
                    - 사용 형태 : 커서명%커서속성
                    - 예 : c1%ROWCOUNT
                - 묵시적 커서 속성
                    - 사용형태 : SQL%커서속성
                    - 예 : SQL%ROWCOUNT

        13.5 커서 매개변수
            [개념]
                - 명시적 커서에는 매개변수를 사용할 수 있다.
            [예시]
                DECLARE
                    v_name emp.ename%TYPE;
                    v_empno NUMBER := 7788;

                -- 매개변수 a_empno를 가지는 명시적 커서
                CURSOR ename_cursor(a_empno NUMBER) IS
                    SELECT ename
                        FROM emp
                        WHERE empno = a_empno;
                BEGIN
                    -- 매개변수를 사용하여 커서를 OPEN한다. 매개변수 v_empno는 사번 7788이다.
                    OPEN ename_cursor(v_empno);

                    -- SELECT 결과를 FETCH한다.
                    FETCH ename_cursor INTO v_name;
                    DBMS_OUTPUT.PUT_LINE('이름 = ' || v_name);

                    --커서를 CLOSE한다.
                    CLOSE ename_cursor;
                END;

                - 커서 매개변수를 사용하지 않고 글로벌 변수를 사용한 경우

        13.6 커서 변수(REF CURSOR)
            [개념]
                - 커서 변수는 명시적 커서와 상당히 유사한데, 명시적 커서가 가지는 네 개의 커서 속성도 동일하게 가진다.
            [구조]
                TYPE 타입명 IS REF CURSOR [ RETURN 반환데이터타입 ];
                변수명 타입명;
            [종류]
                - 커서변수는 RETURN 타입의 지정 여부에 따라서 나뉜다.
                1. 강한 타입 : RETURN 타입을 지정하는 REF CURSOR 타입.
                2. 약한 타입 : RETURN 타입을 지정하지 않는 REF CURSOR 타입
            [예제]
                - 13-17 강한 타입의 커서 변수는 반환형만 일치하면 어떤 SELECT 문도 OPEN할 수 있다.
                - 13-18 커서 변수는 서브프로그램의 매개변수로 사용할 수 있다.
                - 13-19 약한 타입의 커서 변수는 반환형이 서로 다른 쿼리에 대해서도 사용할 수 있다.



     */





}
