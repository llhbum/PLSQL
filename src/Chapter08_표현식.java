public class Chapter08_표현식 {
    /*
    Chapter8_표현식
        [개념]
            - 표현식을 구성하는 요소를 크게 구분해 보면 연산자와 피 연산자다. 연산자와 피연산자가 표현식을 구성하고, 그 표현식의 연산은 하나의 결과값을 만들어 내는 것이다.
    8.4.7 CASE 표현식
        [종류]
            - 단순 CASE 표현식
                [구조]
                    CASE <선택자> WHEN
                    < 선택자값1 >

                    CASE 선택자
                        WHEN 선택값1 THEN 결과값1
                        WHEN 선택값2 THEN 결과값2
                        ...
                        WHEN 선택값n THEN 결과값n
                        [ELSE else 결과값]
                    END;
                [설명]
                    선택자가 가질 수 있는 선택값을 하나씩 검사하는 방식

            - 조사 CASE 표현식
                [구조]
                    CASE WHEN < 조건식1 >

                    CASE
                        WHEN 조건식1 THEN 결과값1
                        WHEN 조건식2 THEN 결과값2
                        ...
                        WHEN 조건식n THEN 결과값n
                        [ELSE else 결과값]
                    END
                [설명]
                    선택자를 사용하지 않으며, WHEN에 따라오는 조건으로 조건식을 사용한다.

    8.5 PL/SQL에서 내장 SQL 함수의 사용
        - DECODE 사용불가
            -> CASE 대치

     */
}
