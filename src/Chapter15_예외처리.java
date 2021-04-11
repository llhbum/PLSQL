public class Chapter15_예외처리 {
    /*
    Chapter15_예외처리
        [개념]
            - PL/SQL의 예외 처리도 PL/SQL 프로그램 수행 중에 비정상적인 상황이 발생할 떄
              이를 처리하는 절차를 명시할 수 있도록 한다.
              예외가 발생하는 원인은 설계상의 오류, 프로그래밍의 실수, 하드웨어의 문제, 인터페이스의 변경 등 너무나 다양한다.
              발생 가능한 모든 예외를 예측하고 이들 각각을 처리하도록 프로그램을 작성하는 것은 현실적으로 불가능할 수도 있다.
              하지만 발생 가능한 예외에 대해 꼼꼼하게 예외 처리를 해주는 것이 탄탄한 프로그램을 작성하는 길임은 두말할 나위도 없다.

              컴파일 시에 발생하는 예외는 예외 처리기와 관련이 없고, 실행 시에 발생하는 예외만 예외 처리기를 사용하여 처리 가능하다.

    * 15.1 예외 처리방법
        - 오라클 PL/SQL에서는 예외 처리를 위해 EXCEPTION절을 사용한다. EXCEPTION절은 기본적으로 다음과 같이 정의한다.

        [예제]
            BEGIN
                ...
            EXCEPTION WHEN 예외명 THEN
                -- 예외처리문
                ...
            END;

        - 예외 처리기는 여러 개의 문장에 대해서도 한번에 예외 처리를 할 수 있다.

    * 15.2 예외의 이름
        - EXCEPTION절에서는 처리 대상 예외명을 지정해야 한다. 예외명에는 오라클에서 기본적으로 제공하는 표준 예외명과
          사용자가 지정한 사용자 예외명 두 가지가 있다.
          ** 243p 참고
        - OTHERS
            - 표준 예외명 중 특별한 예외명으로 OTHERS가 있다.
              OTHERS는 발생하는 어떤 예외라도 처리할 수 있는 매우 특별한 예외다.
        [예제]
            EXCEPTION
                WHEN NO_DATE_FOUND THEN
                    ...
                WHEN TOO_MANY_ROWS THEN
                    ...
                WHEN OTHERS THEN -- 어떤 오류가 발생하더라도 처리한다.
                    ...
           END;

        15.2.2 사용자 정의 예외명
            - 표준 예외명 중 OTHERS를 제외한 예외명은 특정 오류 코드에 일대일로 대응되어 있다.
              표준 예외로 정의되지 않은 예외에 대해 예외명을 사용하고자 할 때에는 사용자 정의 예외명을 사용할 수 있다.
              사용자 정의 예외명은 DECLARE 블록에서 키워드 EXCEPTION을 사용하여 선언되어야 한다.

            [예제]
                DECLARE
                    no_emp_found EXCEPTION -- 사용자 지정 예외 선언
                    v_cnt PLS_INTEGER;
                    v_empno emp.empno%TYPE;
                BEGIN
                    ...
                    IF v_cnt = 0 THEN -- 사원이 존재하지 않으면 사용자 예외 발생
                        RAISE no_emp_found;
                    END IF;
                     ...
                 EXCEPTION WHEN no_emp_found THEN -- 사용자 지정 예외를 처리
                    ...
                 END;
    * 15.3 사용자가 예외를 발생시키기
        예외는 PL/SQL이나 SQL의 수행 중에 내부적으로 발생할 수도 있고, 사용자가 인위적으로 발생시킬 수도 있다.
        인위적으로 발생시키는 방법은 2가지이다.
        1. RAISE문
        2. RAISE_APPLICATION_ERROR
        [예제]
            DBMS_STANDARD을 생략한 형태
                RAISE_APPLICATION_ERROR(오류 번호, 오류 메시지);
            DBMS_STANDARD을 사용하는 형태
                DBMS_STANDARD.RAISE_APPLICATION_ERROR(오류 번호, 오류 메시지);

    * 15.4 예외를 특정 오류 번호와 연결하기
        [개념]
            오라클의 표준 예외 중에서 OTHERS를 제외한 다른 예외들은 오라클 SQL이나 내장 패키지 또는 PL/SQL에서 발생하는
            오류 번호와 연결되어 있다. 사용자 지정 예외는 표준 예외와 같이 예외를 하나의 오라클 오류와 연결하고 이에 이름을 부여하는
            기능을 제공한다.
            사용자 지정 예외명을 특정 오라클 오류와 연결하기 위해서는 PRAGMA EXCEPTION_INIT을 사용한다.

        [형태]
            PRAGMA EXCEPTION_INIT(예외명, 오류번호)

        [예제1]
            DECLARE
                invalide_date EXCEPTION -- 사용자 지정 예외
                PRAGMA EXCEPTION_INIT(invalide_date, -1847) -- ORA-01847 연결
                v_date DATE;
            BEGIN
                -- 오류 유발
                v_date := TO_DATE('2000-12-32', 'YYYY-MM-DD');
            EXCEPTION
                WHEN invalide_date THEN
                    DBMS_OUTPUT.PUT_LINE('날짜 오류가 검춤되었습니다.');
            END;

        [예제2]
            DECLARE
                user_defined_exception EXCEPTION;
                PRAGMA EXCEPTION_INIT(user_defined_exception, -20001)
            BEGIN
                RAISE_APPLICATION_ERROR(-20001, '사용자 지정 예외');
            EXCEPTION
                WHEN user_defined_exception THEN
                    DBMS_OUTPUT.PUT_LINE('사용자 지정 예외 user_defined_exception이 검출되었습니다.');
                    DBMS_OUTPUT.PUT_LINE('SQLCODE = ' || SQLCODE);
            END;

    * 15.5 예외의 전파
        - 기본적으로 예외는 블록 단위로 제어한다.
          한 블록에서 발생하는 예외는 해당 블록에 EXCEPTION WHEN을 사용하는 예외 처리기가 존재하는 경우, 해당 예외 처리기에 의해 처리된다.
          해당 블록이 예외를 처리하지 않는 경우네는 예외가 그 블록을 감싸는 바로 상위 블록으로 전파된다.
          상위 블록이 예외를 처리하지 않으면 다시 그 상위 블록으로 전파되며, 최상위 블록도 예외를 처리하지 않으면 최상위 블록을 호출한 사용자에게
          오류를 전달하면서 프로그램이 종료된다.

          블록 중 하나라도 예외를 처리하면 예외는 더 이상 상위 블록으로 전파되지 않는다.

    * 15.6 예외 처리기에서의 오류 조회 함수
        - 예외 처리기에서는 기본적으로 발생한 예외와 관련되는 오류 번호와 오류 메시지를 조회하는 함수를 사용하여 오류 정보를 확인할 수 있다.
          오류 번호를 조회하는 함수는 SQLCODE
          오류 메시지를 조회하는 함수는 SQLERRM이다.

        [종류]
            1. FORMAT_ERROR_BACKTRACE
                - 오류가 발생한 지점에서 부터 해당 예외를 처리하는 예외처리기까지의 백트레이스를 반환한다.
            2. FORMAT_ERROR_STACK
                - 현재의 오류 스택을 반환한다.
            3. FORMAT_CALL_STACK
                - 함수가 호출되는 지점까지의 콜 스택을 반환한다.

    * 15.7 예외 처리에서 주로 하는 작업
        [종류]
            1. 트랜잭션 마무리
            2. 변수나 반환값 지정
            3. 디버깅 정보 출력
            4. 오류 무시
     */
}
