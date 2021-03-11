public class Chapter09_SQL실행 {
    /*
    Chapter9_SQL실행

        9.1 SELECT문의 사용
            [예제]
               DECLARE
                v_cnt NUMBER;
               BEGIN
                Select count(*) INTO v_cnt
                FROM emp ;
                DBMS_OUPUT.PUT_LINE('COUNT(*) = ' || v_cnt);
               END;

        9.2 INSERT문의 사용
            [예제]
               DECLARE
                    INSERT INTO emp (empno, ename, hiredate, deptno)
                    VALUES (9000,'홍길동',SYSDATE, 30);
                     DBMS_OUPUT.PUT_LINE('INSERT 건수 : ' || SQL%ROWCOUNT); -- 변경된 건수 출력
                     COMMIT;
               END;

        9.3 UPDATE문의 사용
            [예제]
               BEGIN
                UPDATE emp
                    SET deptno = 40
                    WHERE empno = 9000;
                    DBMS_OUPUT.PUT_LINE('UPDATE 건수:' || SQL%ROWCOUNT); -- 변경된 건수 출력
                COMMIT;
               END;

           9.4 MERGE문의 사용
               [개념]
                    MERGE문은 UPSERT(UPDATE + INSERT)

               [예제]
                   BEGIN
                    MERGE INTO emp a
                    USING DUAL
                        ON (a.empno = 9000)
                    WHEN MATCHED THEN -- 사번이 9000인 로우 존재 시
                        UPDATE
                            SET a.comm = a.comm * 1.1
                        WHEN NOT MATCHED THEN -- 사번이 9000인 로우 미존재 시
                            INSERT (empno, ename, job, hiredate, sal, deptno)
                            VALUES (9000, '홍길동', 'CLERK', SYSDATE, 3000, 10);
                        DBMS_OUPUT.PUT_LINE('MERGE 건수 : ' || SQL%ROWCOUNT) ; -- 변경된 건수 출력
                        COMMIT;
                   END;

           9.5 DELETE문의 사용
                [예제]
                   BEGIN
                        DELETE FROM emp
                        WHERE empno = 9000;
                        DBMS_OUPUT.PUT_LINE('INSERT 건수 : ' || SQL%ROWCOUNT); -- 변경된 건수 출력
                        COMMIT;
                   END;

            9.6 시퀀스 사용
            [방법]
                : PL/SQL문에 직접 사용하는 방식, SQL문에 포함하여 사용하는 방식

            [예제]
                - PL/SQL문에 직접 사용하는 방식

                REM 시퀀스 emp_seq 생성
                CREATE SEQUENCE emp_seq;
                   DECLARE
                    v_seq_value NUMBER;
                   BEGIN
                    --SQL 없이 시퀀스를 직접 사용하는 방법
                    v_seq_value := emp_seq.NEXTVAL;

                    DBMS_OUPUT.PUT_LINE('시퀀스 값: ' || TO_CHAR(v_seq_value));
                   END;


                   - SQL문에 포함하여 사용하는 방식
                    DECLARE
                        v_seq_value NUMBER;
                    BEGIN
                        -- SQL을 사용하여 시퀀스 값을 얻어옴
                        -- "v_seq_value := emp_seq.NEXTVAL"에 비해 비효율적임
                        SELECT emp_seq.NEXTVAL INTO v_seq_value
                        FROM dual;

                        DBMS_OUPUT.PUT_LINE('시퀀스 값 : ' TO_CHAR(v_seq_value));
                    END;

                9.7 DML문의 결괏값을 PL/SQL 변수로 반환하는 방법
                    [개념]
                        - INSERT, UPDATE, DELETE문의 RETURNING절이 바로 오라클이 이를 위해 제공하는 확장 기능이다.
                    [구조]
                        RETURNING 표현식1, 표현식2 ...
                        INTO        변수1, 변수2 ...
                    [예제]
                        DECLARE
                            c_hiredate DATE := DATE'2016-01-02';
                            v_empno emp.empno%TYPE;
                            v_ename emp.v_ename%TYPE;
                            v_hiredate emp.v_hiredate%TYPE;
                        BEGIN
                            DELETE FROM emp WHERE empno = 9000; -- 테스트 데이터 삭제
                            -- INSERT 후 삽입된 값을 반환
                            INSERT INTO emp (empno, ename, hiredate, deptno)
                                VALUES(9000, '홍길동' , c_hiredate, 40);
                            RETURNING empno, ename, hiredate
                                INTO v_empno, v_ename, v_hiredate;
                            DBMS_OUPUT.PUT_LINE('사원 추가 = (' || v_empno || ', ' || v_ename || ', ' || TO_CAHR(v_hiredate, 'YYYY-MM-DD') || ')');

                            COMMIT;
                        END
     */
}
