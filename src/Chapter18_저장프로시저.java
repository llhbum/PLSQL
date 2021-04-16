public class Chapter18_저장프로시저 {
    /*
    Chapter18_저장프로시저
        - [개념]
            - PL/SQL 저장 프로시저는 반환되는 값 없이 특정 처리만을 수행하는 서브프로그램이다.
              저장 함수와 마찬가지로 저장 프로시저도 고유한 이름을 가지고, 데이터베이스에 저장되며,
              이름을 식별자로 사용하여 반복적으로 재사용될 수 있다. 저장 프로시저는 입력값으로 매개변수를 가질 수 있다.
              저장 프로시저는 배치 프로그램이나 오라클 스케줄러와 같이 데이터를 처리하는 프로그램에 유용하게 사용된다.

          [구조]
            CREATE [ OR REPLACE ] PROCEDURE 프로시저명 [ ( 매개변수목록 ) ]
            IS -- IS 대신 AS를 사용해도 동일함
                선언부
            BEGIN
                실행부
            EXCEPTION
                예외처리부
            END;

            * 함수 작성 형태와 비교해보면 함수는 반환되는 값의 데이터 타입이 있어야 하지만 프로시저에는 없다는 것과 함수에선,
              반드시 존재해야 하는 실행부의 RETURN 반환값문이 프로시저에는 없다는 것이다.

          [예제]
            Create or replace procedure raise_bonus(a_empno number, a_amt number)
            -- 테이블 bonus에 사원의 커미션 값을 인상하는 프로시저
            IS
                v_ename emp.ename%TYPE;
            BEGIN
                -- 사원의 이름을 얻는다.
                BEGIN
                    SELECT ename
                        INTO v_ename
                    FROM emp
                    WHERE empno = a_empno;
                EXCEPTION
                    WHEN no_data_found then
                    -- 사원이 존재하지 않을 경우는 수행을 중단하고 복귀한다. 반환값이 없는 것이 함수와 다른 점이다.
                        RETURN;
                END;

                -- 보너스를 인상한다.
                IF a_amt IS not null
                THEN
                    merge into bonus
                    using dual
                        on (bonus.ename = v_ename)
                    when matched then -- 기존 보너스가 있는 경우는 인상할 값을 더한다.
                        update set comm + a_amt
                    when not matched then -- 기존 보너스가 없는 경우는 새 로우를 추가한다.
                        insert(ename, comm)
                        values(v_ename, a_amt);
                    end if;
                end;

    * 18.1 프로시저의 매개변수
        - 프로시저의 매개변수 사용 방법 함수에서와 다르지 않다. 매개변수가 없을 경우에는 매개 변수를 감싸는 괄호까지 생략해야 하는 것도 동일하다.
          17.2, 20.1을 참고해라

    * 18.2 프로시저의 선언부
        - 프로시저의 선언부의 사용법은 함수에서의 사용법과 동일하다. 17.3을 참고해라

    * 18.3 저장 프로시저의 사용
        - 저장 프로시저의 사용방법 16.4를 참고해라

     */
}
