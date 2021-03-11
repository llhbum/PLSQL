public class Chapter07_변수와상수그리고리터널 {
    /*
    Chapter7_변수와상수그리고리터널

        7.1 변수
            [개념]
                - 값을 변경시킬 수 있는 저장소, 블록의 선언부(DECLARE과 BEGIN 사이)에서 선언
            [구조]
                변수명 데이터타입 [NOT NULL] [:= 초깃값];
        7.2 상수
            [개념]
                - 상수는 선언과 동시에 값이 정의되며, 그 후로 값을 변경할 수 없는 저장소다.
            [구조]
                상수명 CONSTANT 데이터타입 := 초깃값;
        7.3 리터럴
            [개념]
                - 소스 코드의 고정된 값
            [예시]
                c_pi CONSTANT NUMBER := 3.14;
                상수                     리터럴
            - 접두어 문자 Q를 사용하는 문자형 리터럴
                [예시]
                    DECLARE
                        v_SQL VARCHAR2(1000);
                    BEGIN
                        v_SQL := Q'[SELECT EMPNO, ENAME FROM EMP WHERE ENAME IN ('SMITH', "ALLEN', 'WARD', 'JONES', 'MARTIN',)]';
                        DBMS_OUTPUT.PUT_LINE(v_SQL);
                    END;
                - 원 SQL 문장의 앞에 Q'[를 추가하고, 뒤에 ]'를 추가했다. 이 방법을 사용하면 문자열 중간에 나타나는 작은 따옴표를 연속되는 두 개의 작은 따옴표를 바꾸지 않아도 된다.
                [구조]
                    Q + ' + 시작구분자 + 원본문자열 + 끝구분자 + '
     */
}
