public class Chapter04_PLSQL프로그램기본구조 {

    /*
    * 4.1 블록 구조
        - PL/SQL 프로그램의 두 가지 유형인 익명PL/SQL 블록과 저장 서브프로그램은 모두 블록구조이다.

        [구조]
        DECLARE
            선언부(옵션) : 타입, 상수, 예외, 커서, 서브프로그램 등의 선언 및 정의
            * 선언부에 올 수 있는 것들 중에서는 서브프로그램(함수와 프로시저)이 가장 뒤에 와야한다.
        BEGIN
            실행부(필수) : Logic 처리
        EXCEPTION
            예외처리부(옵션) : 실행부에서 발생하는 예외 사항 처리
        END;

        [예시]
        DECLARE
            v_str varchar2(100);
        BEGIN
            v_str := 'Hello World';
            DBMS_OUTPUT.PUT_LINE(v_str);
        EXCEPTION WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
        END:

     * 4.4 저장 서브프로그램과 익명 PL/SQL
        - 저장 서브프로그램 : 컴파일되어 DB의 데이터 딕셔너리에 소스코드와 함께 바이트 코드 형태로 저장됨, 차후에 이름을 식별자로 실행
        [예시]
        CREATE OR REPLACE FUNCTION square(a_num Number)
        RETURN NUMBER
        IS
        BEGIN
            RETURN a_num * a_num;
        END;

        - 익명 PL/SQL : 즉시 실행되며, DB의 데이터 딕셔너리에는 저장되지 않는다.
        [예시]
        DECLARE
            v_num NUMBER;
        BEGIN
            v_num := square(10);
            DBMS_OUTPUT.PUT_LINE(v_num);
        END;

     */
}
