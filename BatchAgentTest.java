package com.ism3.agent.batch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

public class BatchAgentTest {

    public static final String PREFIX_DASH = "-";
    public static final String EMPTY_STRING = "";

    static {
        Properties log4jProperty = new Properties();
        log4jProperty.put("log4j.rootLogger", "DEBUG, stdout");
        log4jProperty.put("log4j.appender.stdout", ConsoleAppender.class.getName());
        log4jProperty.put("log4j.appender.stdout.Threshold", "INFO");
        log4jProperty.put("log4j.appender.stdout.layout", PatternLayout.class.getName());
        log4jProperty.put("log4j.appender.stdout.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%t] %m%n");

        PropertyConfigurator.configure(log4jProperty);
    }
    private static Hashtable<String, String> parseArgument(String[] args) {
        Hashtable<String, String> options = new Hashtable<String, String>();
        for (int x = 0; x < args.length; x++) {
            if (args[x] != null && args[x].startsWith(PREFIX_DASH)) {
                if ((x + 1) < args.length) {
                    if (args[x + 1] != null && !args[x + 1].startsWith(PREFIX_DASH)) {
                        options.put(args[x], args[x + 1]);
                    } else {
                        options.put(args[x], EMPTY_STRING);
                    }
                } else {
                    options.put(args[x], EMPTY_STRING);
                }
            }
        }
        return options;
    }

    @Test
    public void test_ISMBAT000001() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBAT000001",
                "-f", "source.txt",
                "-t", "target.txt"
                });
        int result = agent.execute(options);
        assertEquals("ISMBAT000001 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    @Test
    public void test_ISMBAT000001_Error() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBAT000001",
                "-f", "11/source.txt1",
                "-t", "target.txt"
                });
        int result = agent.execute(options);
        System.err.println(" test_ISMBAT000001_Error  " + agent.getResultCode() + " " + agent.getResultMessage());
        assertNotEquals("ISMBAT000001 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }

    @Test
    public void test_ISMBAT002001() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBAT002001",
                });
        int result = agent.execute(options);
        assertEquals("ISMBAT002001 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }

    @Test
    public void test_ISMBAT002002() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBAT002002",
                });
        int result = agent.execute(options);
        assertEquals("ISMBAT002002 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01001
    FTP-FTP 파일기본 전송 - 절대경로
    */
    @Test
    public void CASE_S_01001() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01001", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01001 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01002
    FTP-FTP 파일기본 전송 - 상대경로
    */
    @Test
    public void CASE_S_01002() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01002", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01002 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01003
    FTP-FTP 파일기본 전송 - Agent 옵션 절대경로
    */
    @Test
    public void CASE_S_01003() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01003",
                "-f", "/home/ism3/src/file001k",
                "-t", "/home/ism3/tgt/test01003"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01003 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01004
    FTP-FTP 파일기본 전송 - Agent 옵션 상대경로
    */
    @Test
    public void CASE_S_01004() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01004",
                "-f", "src/file001k",
                "-t", "tgt/test01004"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01004 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01005
    FTP-FTP 파일기본 전송 - Agent 옵션 절대경로 - 우선순위(Agent옵션이 우선순위어야 함)
    */
    @Test
    public void CASE_S_01005() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01005",
                "-f", "/home/ism3/src/file101k",
                "-t", "/home/ism3/tgt/test11005"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01005 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01006
    FTP-FTP 파일기본 전송 - Agent 옵션 상대경로 - 우선순위(Agent옵션이 우선순위어야 함)
    */
    @Test
    public void CASE_S_01006() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01006",
                "-f", "sub1/file001k",
                "-t", "sub1/test11006"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01006 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01007
    FTP-FTP 파일기본 전송 - 송신파일 경로 / & 수신파일 절대경로
    */
    @Test
    public void CASE_S_01007() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01007", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01007 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01008
    FTP-FTP 파일기본 전송 - 송신파일 경로 / & 수신파일 상대경로
    */
    @Test
    public void CASE_S_01008() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01008", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01008 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01009
    FTP-FTP 파일기본 전송 - 송신파일 절대경로 & 수신파일 경로 /
    */
    @Test
    public void CASE_S_01009() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01009",

                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01009 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01010
    FTP-FTP 파일기본 전송 - 송신파일 상대경로 & 수신파일 경로 /
    */
    @Test
    public void CASE_S_01010() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01010", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01010 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01011
    FTP-FTP 파일기본 전송 - 절대경로 & 송신파일명만 있고 수신파일명은 없는 경우
    */
    @Test
    public void CASE_S_01011() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01011", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01011 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01012
    FTP-FTP 파일기본 전송 - 상대경로 & 송신파일명만 있고 수신파일명은 없는 경우
    */
    @Test
    public void CASE_S_01012() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01012", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01012 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01013
    FTP-FTP 파일기본 전송 - 절대경로 & 송신파일명은 없고 수신파일명만 있는 경우
    */
    @Test
    public void CASE_S_01013() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01013", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01013 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01014
    FTP-FTP 파일기본 전송 - 상대경로 & 송신파일명은 없고 수신파일명만 있는 경우
    */
    @Test
    public void CASE_S_01014() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01014", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01014 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01015
    FTP-FTP - 절대경로 & 송?수신 파일명이 한글인 경우
    */
    @Test
    public void CASE_S_01015() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01015", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01015 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01016
    FTP-FTP - Agent옵션 -절대경로 & 송?수신 파일명이 한글인 경우
    */
    @Test
    public void CASE_S_01016() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01016",
                "-f", "/home/ism3/src/파일001k",
                "-t", "/home/ism3/tgt/테스트01016"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01016 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01017
    FTP-FTP - 상대경로 & 송?수신 파일명이 한글인 경우
    */
    @Test
    public void CASE_S_01017() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01017", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01017 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01018
    FTP-FTP - Agent옵션 - 상대경로 & 송?수신 파일명이 한글인 경우
    */
    @Test
    public void CASE_S_01018() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01018",
                "-f", "src/파일001k",
                "-t", "tgt/테스트01018"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01018 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01019
    FTP-FTP - 상대경로 & 송?수신 파일명에 공백이 포함된 경우
    */
    @Test
    public void CASE_S_01019() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01019", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01019 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01020
    FTP-FTP - Agent옵션 - 상대경로 & 송?수신 파일명에 공백이 포함된 경우
    */
    @Test
    public void CASE_S_01020() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01020",
                "-f", "/home/ism3/src/file 001k",
                "-t", "/home/ism3/tgt/test 01020"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01020 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01021
    FTP-FTP - 절대경로 & 송?수신 파일명에 특수문자가 포함된 경우
    */
    @Test
    public void CASE_S_01021() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01021", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01021 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01022
    FTP-FTP - Agent 옵션 송신파일명 절대경로 & 수신파일명 상대경로
    */
    @Test
    public void CASE_S_01022() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01022",
                "-f", "/home/ism3/src/file001k",
                "-t", "tgt/test01022"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01022 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01023
    FTP-FTP - Agent 옵션 송신파일명 상대경로 & 수신파일명 절대경로
    */
    @Test
    public void CASE_S_01023() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01023",
                "-f", "src/file001k",
                "-t", "/home/ism3/tgt/test01023"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01023 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01024
    FTP-FTP - 송신파일명 절대경로 & 수신파일명 상대경로
    */
    @Test
    public void CASE_S_01024() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01024", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01024 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01025
    FTP-FTP - 송신파일명 상대경로 & 수신파일명 절대경로
    */
    @Test
    public void CASE_S_01025() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01025", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01025 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01026
    FTP-FTP - 2GB송신 파일(대용량 파일)
    */
    @Test
    public void CASE_S_01026() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01026", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01026 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01027
    FTP-FTP - 다중파일전송 - ? * 로 다중 파일이 전송될때 제대로 전송 되는지(ftp명령어)
    */
    @Test
    public void CASE_S_01027() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01027", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01027 result fail - " + result, BatchRequest.PROCESS_OK, result);
    
    }
        
    /*
    ISMBNFF01028
    FTP-FTP 파일기본 전송 - 송신파일 절대경로 & 수신파일 경로 / (root 계정)
    */
    @Test
    public void CASE_S_01028() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01028", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01028 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01029
    FTP-FTP 파일기본 전송 - 송신파일 상대경로 & 수신파일 경로 / (root 계정)
    */
    @Test
    public void CASE_S_01029() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01029", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01029 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01030
    FTP-FTP 파일기본 전송 - Agent 옵션 송신파일 절대경로 & 수신파일 경로 / (root 계정)
    */
    @Test
    public void CASE_S_01030() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01030", 
                "-f", "/home/ism3/src/file001k",
                "-t", "/"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01030 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF01031
    FTP-FTP 파일기본 전송 - Agent 옵션 송신파일 상대경로 & 수신파일 경로 / (root 계정)
    */
    @Test
    public void CASE_S_01031() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF01031", 
                "-f", "./src/file001k",
                "-t", "/"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF01031 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
        
    /*
    ISMBNFF91001
    FTP-FTP 파일 에러 확인 - Agent 옵션 절대경로 송신파일이 존재하지 않는 경우
    */
    @Test
    public void CASE_F_91001() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91001", 
                "-f", "/home/ism3/src/file001k",
                "-t", "/home/ism3/tgt/test91001"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91001 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF91002
    FTP-FTP 파일 에러 확인 - Agent 옵션 상대경로 송신파일이 존재하지 않는 경우
    */
    @Test
    public void CASE_F_91002() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91002", 
                "-f", "src/file001k",
                "-t", "tgt/test91002"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91002 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF91003
    FTP-FTP 파일 에러 확인 - Agent 옵션 절대경로 송신파일이 존재하지 않는 경우
    */
    @Test
    public void CASE_F_91003() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91003", 
                "-f", "/home/ism3/src/file001k",
                "-t", "/home/ism3/tgt/test91003"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91003 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF91004
    FTP-FTP 파일 에러 확인 - 절대경로 송신파일 파일은 존재하는데 read 권한이 없는 경우
    */
    @Test
    public void CASE_F_91004() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91004", 
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91004 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF91005
    FTP-FTP 파일 에러 확인 - Agent 옵션 수신파일은 존재하는데 write 권한이 없는 경우
    */
    @Test
    public void CASE_F_91005() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91005", 
                "-f", "/home/ism3/src/file001k",
                "-t", "/home/ism3/tgt/test91005"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91005 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF91006
    FTP-FTP 파일 에러 확인 - Agent 옵션 절대경로 송신파일의 디렉토리가 존재하지 않는 경우
    */
    @Test
    public void CASE_F_91006() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91006", 
                "-f", "/home/ism3/src/tmp/file001k",
                "-t", "/home/ism3/tgt/test91006"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91006 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF91007
    FTP-FTP 파일 에러 확인 - Agent 옵션 절대경로 송신파일의 디렉토리가 존재하지 않는 경우
    */
    @Test
    public void CASE_F_91007() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91007", 
                "-f", "src/tmp/file001k",
                "-t", "tgt/test91007"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91007 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF91008
    FTP-FTP 파일 에러 확인 - Agent 옵션 송신경로가 존재하지 않는 경우
    */
    @Test
    public void CASE_F_91008() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91008", 
                "-f", "file001k",
                "-t", "/home/ism3/tgt/test91008"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91008 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
    
    /*
    ISMBNFF91009
    FTP-FTP 파일 에러 확인 - Agent 옵션 수신경로가 존재하지 않는 경우
    */
    @Test
    public void CASE_F_91009() {
        BatchAgent agent = new BatchAgent();
        Hashtable<String, String> options = parseArgument(new String[] {
                "-conf", "./src/main/resources/conf/agent.properties", 
                "-i", "ISMBNFF91009", 
                "-f", "/home/ism3/src/file001k",
                "-t", "test91009"
                });
        int result = agent.execute(options);
        assertEquals("ISMBNFF91009 result fail - " + result, BatchRequest.PROCESS_OK, result);
    }
}