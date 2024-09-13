package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.MembersDAO;
import dto.MembersDTO;

public class MembersService {

    private Scanner sc = new Scanner(System.in);
    private MembersDAO membersDao = new MembersDAO();
    private List<MembersDTO> memList = null;
    private MembersDTO membersDto = null;
    
    boolean a = true; // while 루프의 조건 변수
    public List<MembersDTO> loginService() {
        boolean a = true;
        List<MembersDTO> memList = null;
        
        while (a) {
            System.out.println("아이디를 입력해 주세요: ");
            String memId = sc.nextLine();

            System.out.println("비밀번호를 입력해 주세요: ");
            String pwd = sc.nextLine();

            memList = membersDao.login(memId, pwd); // 로그인 시도

            // 로그인 성공
            if (memList != null && memList.size() > 0) {
                a = false; // 루프 종료
            } else {
                System.out.println("로그인 실패. 다시 시도해주세요.");
                System.out.println("1. 다시 시도");
                System.out.println("2. 메인으로");
                int tempSel = Integer.parseInt(sc.nextLine());

                if (tempSel == 1) {
                    continue;
                } else if (tempSel == 2) {
                    a = false; // 루프 종료
                    memList = null; // 로그인 실패 시 null 반환
                }
            }
        }
        return memList;
    }

	
	public List<MembersDTO> signUpService() {
		
        System.out.println("=========================================");
        System.out.println("               회원가입 화면                ");
        System.out.println("=========================================");

        String memId = null;
        String pwd = null;
        String memName = null;
        String email = null;
        String birth = null;
        String phone = null;
        String gender = null;
        String address = null;

        boolean isValid = false;

     // 아이디 입력 및 유효성 검사
        while (!isValid) {
            System.out.println("=========================================");
            System.out.println("      아이디 입력 (5~20자 영문, 숫자 포함)            ");
            System.out.println("=========================================");
            System.out.print("  아이디: ");
            memId = sc.nextLine();
            if (memId.matches("^[a-zA-Z0-9]{5,20}$")) {
                isValid = true;
            } else {
                System.out.println("유효하지 않은 아이디입니다. 다시 입력해주세요.");
                if (returnToMainMenu() == 1) {  // returnToMainMenu가 1을 반환하면 메인메뉴로 돌아감
                    return null;
                }
            }
        }

        isValid = false;

        // 비밀번호 입력 및 유효성 검사
        while (!isValid) {
            System.out.println("=========================================");
            System.out.println("  비밀번호 입력 (8~20자 영문, 숫자, 특수문자 포함)     ");
            System.out.println("=========================================");
            System.out.print("  비밀번호: ");
            pwd = sc.nextLine();
            if (pwd.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$")) {
                isValid = true;
            } else {
                System.out.println("유효하지 않은 비밀번호입니다. 다시 입력해주세요.");
                if (returnToMainMenu() == 1) {  // 메인메뉴로 돌아갈 경우 처리
                    return null;
                }
            }
        }

        // 이름 입력
        System.out.println("=========================================");
        System.out.println("                이름 입력                  ");
        System.out.println("=========================================");
        System.out.print("  이름: ");
        memName = sc.nextLine();

        // 이메일 입력 및 유효성 검사
        isValid = false;
        while (!isValid) {
            System.out.println("=========================================");
            System.out.println("               이메일 입력                ");
            System.out.println("=========================================");
            System.out.print("  이메일: ");
            email = sc.nextLine();

            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();
            if (isValid) {
                System.out.println("유효한 이메일입니다.");
            } else {
                System.out.println("유효하지 않은 이메일입니다. 다시 입력해주세요.");
                if (returnToMainMenu() == 1) {  // 메인메뉴로 돌아갈 경우 처리
                    return null;
                }
            }
        }

        // 생일, 전화번호, 성별, 주소 입력
        System.out.println("=========================================");
        System.out.println("                 생일 입력                   ");
        System.out.println("=========================================");
        System.out.print("생일: ");
        birth = sc.nextLine();

        System.out.println("=========================================");
        System.out.println("                전화번호 입력                ");
        System.out.println("=========================================");
        System.out.print("전화번호: ");
        phone = sc.nextLine();

        System.out.println("=========================================");
        System.out.println("                 성별 입력                   ");
        System.out.println("=========================================");
        System.out.print("성별: ");
        gender = sc.nextLine();

        System.out.println("=========================================");
        System.out.println("                 주소 입력                   ");
        System.out.println("=========================================");
        System.out.print("주소: ");
        address = sc.nextLine();

        // MembersDTO 객체 생성 및 회원가입 처리
        MembersDTO mDTO = new MembersDTO(memId, pwd, memName, email, birth, phone, gender, address, null, null, null);
        int result = membersDao.signUp(mDTO);

        // 로그인 정보 가져오기
        List<MembersDTO> memList = membersDao.login(memId, pwd);

        // 결과 출력
        if (result > 0) {
            System.out.println("=========================================");
            System.out.println("       회원가입이 성공적으로 완료되었습니다.          ");
            System.out.println("=========================================");
        } else {
            System.out.println("=========================================");
            System.out.println("     회원가입에 실패했습니다. 다시 시도해주세요.        ");
            System.out.println("=========================================");
        }

        return memList;
    }


	public List<MembersDTO> editInfoService(List<MembersDTO> loginInfo) {

        List<MembersDTO> memList = new ArrayList<>();
        boolean isPasswordCorrect = false;

        // 비밀번호 확인 단계
        while (!isPasswordCorrect) {
            System.out.println("=========================================");
            System.out.println("         회원정보 수정 - 비밀번호 확인           ");
            System.out.println("=========================================");
            System.out.print("다시 한번 비밀번호를 입력해주세요: ");
            String pwd = sc.nextLine();

            // 입력한 비밀번호가 현재 로그인된 회원 정보의 비밀번호와 일치하는지 확인
            if (loginInfo.get(0).getPwd().equals(pwd)) {
                isPasswordCorrect = true;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
                // 메인 메뉴로 돌아갈지 확인
                int returnValue = returnToMainMenu();
                if (returnValue == 1) { // 1이면 메인메뉴로 돌아가기
                    return loginInfo; // 메인메뉴로 돌아가면 현재 로그인 정보 반환
                }
            }
        }

        // 비밀번호가 맞았을 경우, 회원정보 수정
        System.out.println("=========================================");
        System.out.println("        회원정보 수정 - 새로운 정보 입력         ");
        System.out.println("=========================================");

        System.out.print("바꿀 비밀번호를 입력해 주세요: ");
        String newPwd = sc.nextLine();

        System.out.print("바꿀 이름을 입력해 주세요: ");
        String memName = sc.nextLine();

        System.out.print("바꿀 이메일을 입력해 주세요: ");
        String email = sc.nextLine();

        System.out.print("바꿀 생일을 입력해 주세요: ");
        String birth = sc.nextLine();

        System.out.print("바꿀 전화번호를 입력해 주세요: ");
        String phone = sc.nextLine();

        System.out.print("바꿀 성별을 입력해 주세요: ");
        String gender = sc.nextLine();

        System.out.print("바꿀 주소를 입력해 주세요: ");
        String address = sc.nextLine();

        // 새로 입력받은 정보로 DTO 생성
        MembersDTO mDto = new MembersDTO(loginInfo.get(0).getMemId(), newPwd, memName, email, birth, phone, gender, address, null, null, null);

        // DAO를 통해 회원 정보 업데이트
        int result = membersDao.updateMem(mDto);

        // 처리 결과 출력
        if (result > 0) {
            System.out.println("회원 정보가 성공적으로 수정되었습니다.");
            memList.add(0, mDto);
        } else {
            System.out.println("회원 정보 수정에 실패했습니다. 다시 시도해주세요.");
        }

        return loginInfo;
    }
    
    private int returnToMainMenu() {
        System.out.print("메인 메뉴로 돌아가시겠습니까? (1: 예, 0: 아니오): ");
        String choice = sc.nextLine();
        if (choice.equals("1")) {
            System.out.println("메인 메뉴로 돌아갑니다.");
            return 1; // 메인 메뉴로 돌아가기
        }
        return 0;
    }

}
