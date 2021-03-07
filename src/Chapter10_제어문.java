public class Chapter10_제어문 {
    /*
    Chapter10_제어문
        10.1 제어문의 종류
            - 조건 분기문 : 주어진 조건에 따라서 서로 다른 문장을 실행할 수 있도록 하는 제어문 : IF문 CASE문
            - 무조건 분기문 : 조건없이 프로그램의 다름 실행 위치를 변경하는 제어문 : GOTO문
            - 순환문 : 주어진 조건을 만족하는 동안 특정 범위의 문장들을 반복적으로 실행할 수 있도록 하는 제어문 : 기본 LOOP문, WHILE LOOP문, FOR LOOP문, EXIT문, CONTINUE문

        10.2.1 IF문
            [구조]
                DECLARE
                ...
                BEGIN
                ...
                IF 조건식 THEN
                ...
                ELSIF 조건문 THEN
                ...
                ...
                ELSE
                ...

        10.4 레이블
            - 소스 코드 내의 특정 문장을 가리키는 식별자다.
            - GOTO문에서 다음 실행할 위치를 지정하기 위해서는 반드시 레이블을 사용해야만 한다.

            [구조]
                <<레이블명>>

        10.5 순환문
            - 기본 LOOP문
            [구조]
                LOOP
                    실행문
                END LOOP;

            - 탈출문
            [종류]
                GOTO : LOOP 밖에 선언된 레이블 다음의 실행문으로 실행 위치를 이동
                EXIT : LOOP를 즉시 탈출하여 END LOOP 다음의 실행문으로 실행위치를 이동
                EXIT WHEN : WHEN 조건을 만족 시 END LOOP 다음의 실행문으로 실행 위치를 이동

            - WHILE LOOP문
            [구조]
                WHILE 조건식
                LOOP
                    실행문
                END LOOP;

            [예제]
                DECLARE
                    v_num NUMBER := 1;
                BEGIN
                    WHILE v_num <= 3
                    LOOP
                        DBMS_OUTPUT.PUT_LINE('루프 내부 : ' || v_num);
                        v_num := v_num+1;
                    END LOOP;
                    DBMS_OUTPUT.PUT_LINE('루프종료 : ' || v_num);

            - FOR LOOP문
            [구조]
                FOR 인덱스변수 IN [REVERSE] 하한값 .. 상한값
                LOOP
                    실행문
                END LOOP;

            [예시]
                BEGIN
                    FOR idx IN 1 .. 3
                    LOOP
                        DBMS_OUTPUT.PUT_LINE('루프 내부 : ' || idx);
                    END LOOP;
                END;

            - LOOP문 내에서의 흐름 변경
                [개념]
                    LOOP문의 흐름을 변경 할 수 있는 또 다른 기능을 가진 제어문을 CONTINUE와 CONTINUE WHEN문이다.
                [종류]
                    CONTINUE : 수행 중인 LOOP에서 수행 위치를 무조건 루프의 첫 문장으로 변경(CONTINUE문 아래의 문장들은 실행되지 않는다)
                    CONTINUE WHEN : WHEN 조건을 만족시 수행 위치를 루프의 첫 문장으로 변경(CONTINUE WHEN문 아래의 문잘들은 실행되지 않는다.)

     */
}
