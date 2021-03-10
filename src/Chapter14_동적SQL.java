public class Chapter14_동적SQL {
    /*
    Chapter14_동적SQL
        [비교]
            PL/SQL에서 SQL문을 사용하는 방법은 크게 두 가지로, 정적SQL과 동적SQL로 분류할 수 있다.
            정적SQL : PL/SQL 프로그램 안에 SQL문을 직접 삽입
                [예제]
                    SELECT COUNT(*) INTO v_cnt
                    FROM EMP;
                [사용가능한 SQL]
                    - DML, TCL

            동적SQL : 실행 중 변경 가능한 문자열 변수 또는 문자열 상수로 제공된 SQL문을 실행
                [예제]
                    EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM emp' INTO v_cnt;
                [사용가능한 SQL]
                    모든 SQL문

        [실행방법]
            - 1. EXECUTE IMMEDIATE문을 사용하여 SQL을 실행하는 방법
            - 2. 커서 변수를 사용하여 OPEN, FETCH, CLOSE문으로 SELECT문을 실행하는 방법
            - 오라클 내장 패키지인 DBMS_SQL을 사용하는 방법

    * 14.1 EXECUTE IMMEDIATE문을 사용하는 방법
        [개념]
            - 실행 중 변경 가능한 문자열 변수 또는 문자열 상수로 제공된 SQL문을 실행/ 모든 단계가 런타임에 수행/ 동적 SQL을 사용하여 런타임에 구조를 변경할 수 있는 SQL문 생성 가능
        [구조]
            - EXECUTE IMMEDIATE SQL문자열 [INTO 변수목록] [USING 바인드변수목록];
        [예제]
            - EXECUTE IMMEDIATE를 사용한 DDL, DML, TCL 실행
            DECLARE
                v_insert_stmt CONSTANT VARCHAR2(100) := 'INSERT INTO t VALUES(1, "서울")' ;
            BEGIN
                -- DDL 실행 리터럴 사용
                EXCEPTION WHEN OTHERS THEN
                NULL ; -- 테이블이 없을 때 발생하는 오류는 무시함
                END;
                EXECUTE IMMEDIATE ' CREATE TABLE t(a NUMBER, b VARCHAR2(10))' ;

                -- DML 실행, 문자열 변수 사용
                EXECUTE IMMEDIATE v_insert_stmt;

                -- TCL실행. 리터럴 사용
                EXECUTE IMMEDIATE 'COMMIT';
            END;

        - EXECUTE IMMEDIATE문에서 쿼리 결과를 변수에 저장

        14.1.2 바인드 변수의 사용
        [개념]
            ':플레이스홀더명'
        [예제]
            - EXECUTE IMMEDIATE문에서 바인드 변수 사용
            DECLARE
                v_query CONSTANT VARCHAR2(200) := 'select count(*) from emp where deptno = : deptno AND job = :job

                v_deptno emp.deptno%TYPE;
                v_cnt PLS_INTEGER;
            BEGIN
                v_deptno := 20;
                -- 바인드 값은 변수, 상수, 리터럴을 모두 사용할 수 있다.
                EXECUTE IMMEDIATE v_query INTO v_cnt
                USING IN v_deptno, 'CLERK';
                DBMS_OUTPUT.PUT_LINE('COUNT = ' || v_cnt);
            END:

        14.1.3 바인드 변수의 모드
            [종류]
                USING절에 사용되는 바인드 변수는 값이 전달되는 방향에 따라서 세 가지 모드로 사용할 수 있다.
                - 입력(IN) 모드 : 바인드 변수 앞에 키워드 'IN'을 지정하며, 값이 PL/SQL에서 데이터베이스 서버로 전달된다.
                                입력 모드 변수의 값은 동적 SQL의 실행 전후에 변경이 없다. 모드의 생략 시 이 모드가 기본값
                - 출력(OUT) 모드 : 바인드 변수 앞에 키워드 OUT을 지정하며 값이 DB 서버에서 PL/SQL로 전달된다.
                                 동적 SQL을 실행하기 전에 바인드 변수가 가지고 있던 값은 실행 결과에 아무런 영향도 끼치지 않는다.
                - 입출력(IN OUT)모드 : 바인드 변수 앞에 IN OUT을 지정하며, 값이 양방향으로 전달된다. 동적 SQL 실행하기 전에 가지고 있던 값이 DB 서버로 전달되어 참조됨,
                                    DB 서버에서 변견된 값이 다시 PL/SQL변수로 되돌려진다.

        14.1.4 바인드 변수 플레이스 홀더의 이름과 순서
            [개념]
                바인드 변수가 여러 개 사용될 떄 SQL문의 종류에 따라서 플레이스 홀더와 바인드 값의 사용 방법이 달라진다.
            [종류]
                가. 익명 PL/SQL문도 아니고 CALL문도 아닐 경우
                    - 플레이스 홀더의 이름은 아무 의미도 없다. 따라서 플레이스 홀더의 이름이 서로 달라야 할 필요도 없다. (가독성을 위해서 구분하자)
                나. 익명 PL/SQL 문이거나 CALL문일 경우
                    - 이 유형에는 플레이스 홀더의 이름과 나타나는 순서가 매우 중요하다.

    14.2 커서 변수를 사용하는 방법
        [예제]
            - 커서 변수를 사용하는 동적SQL
            DECLARE
                TYPE empcur_type IS REF CURSOR;
                v_emp_cur   empcur_type; -- 커서변수
                emp_rec     emp%ROWTYPE;
                v_stmt      VARCHAR2(200);
                v_empno     NUMBER;

            BEGIN
                -- 실행할 동적 SQL문;
                v_stmt := 'SELECT * FROM WHERE empno =: empno';
                v_empno := 7788; -- 바인드 변수의 값으로 사용할 사번

                -- 쿼리문 v_stmt에 대한 v_emp_cur 커서를 OPEN
                OPEN v_emp_cur FOR v_stmt USING v_empno;

                -- 결과 로우를 한 건씩 FETCH
                LOOP
                    FETCH v_emp_cur INTO emp_rec;
                    EXIT WHEN v_emp_cur%NOTFOUND;
                END LOOP;

                -- 사용 완료된 커서를 CLOSE
                CLOSE v_emp_cur;
            END;

    14.3 DBMS_SQL 내장 패키지를 사용하는 방법
        [예제]
            - 14.7 DBMS_SQL을 사용한 SELECT문 처리
            - 14.8 DBMS_SQL을 사용한 DML문 처리

    14.4 동적 PL/SQL
        [개념]
            - PL/SQL도 EXECUTE IMMEDIATE문과 DBMS_SQL패키지를 사용하여 동적으로 실행가능하다.
        [예제]
            DECLARE
                v_stmt VARCHAR2(1000);
                v_empno emp.empno%TYPE;
                v_ename emp.ename%TYPE;
                v_dname dept.dname%TYPE;

            BEGIN
                -- 실행할 동적 PL/SQL문
                -- 사번을 입력으로 하여 사원명과 소석 부서를 출력

                v_stmt := 'DECLARE
                                vv_ename emp.ename%TYPE;
                                vv_dname dept.dname%TYPE;
                            BEGIN
                                DBMS_OUTPUT.PUT_LINE("조회할 사번 = " ||:empno);
                                SELECT ename, dname INTO vv_ename, vv_dname
                                FROM emp e, dept d
                                WHERE e.empno = : empno AND e.deptno = d.deptno;
                                :ename := vv_ename;
                                :dname := vv_dname;
                            END;';
                v_empno := 7788;
                -- 동적PL/SQL문 실행
                EXECUTE IMMEDIATE v_stmt USING IN v_empno , -- 입력변수(IN은 생략 가능)
                                                OUT v_ename  -- 출력변수(OUT 필수)
                                                OUT v_dname; -- 출력변수(OUT 필수)
                DBMS_OUTPUT.PUT_LINE(v_ename ||' 의 소속 부서 = ' || v_dname);
            END;


     */
}
