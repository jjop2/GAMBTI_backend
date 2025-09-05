# GAMBTI
08/25 ~ 09/05 진행된 팀 프로젝트로, 3조가 개발한 React + Spring Boot 기반 풀스택 프로젝트입니다. GAMBTI는 테스트를 통해 자신의 게임 취향을 파악하고, 자신의 취향에 맞는 게임을 추천 받을 수 있는 웹사이트입니다. 테스트를 포함한 대부분의 기능은 JWT 인증을 활용한 회원제로 운영됩니다.

## 주요 기능
- 회원가입 & 로그인
	- 일반 회원가입
	- Google / Kakao 소셜 로그인
	- JWT 기반 인증
- 성향 분석 테스트
	- 설문 기반 성향 분석 후 결과 출력
- 맞춤형 게임 추천
	- 테스트 결과에 따른 상위 장르 2개 추출 → 게임 리스트 추천
	- 추천 게임 클릭 시 해당 스팀 페이지로 이동
- 장르별 게임 추천
- 커뮤니티
	- 게시글 작성, 수정, 삭제, 이미지 업로드
- 마이페이지
	- 회원 정보 조회 및 관리
	- 작성한 글 조회
- 보안
	- Spring Security + JWT 인증 토큰 방식

## 사용한 기술
### Frontend
- React
- React Router (페이지 이동 관리)
- Axios (API 통신)
- react-kakao-login, @react-oauth/google (소셜 로그인)
- font Awesome

### Backend
- Spring Boot
- Spring Security + JWT (인증/인가 및 토큰 기반 로그인)
- Spring Data JPA
- Lombok (코드 간소화)
- Spring Boot DevTools (개발 편의성)
- MySQL Driver (DB 연결)
- Steam API (게임 정보 조회)

### Database
- MySQL
- DB 관리 툴: DBeaver

## 개발자
### Frontend
- 김현태 : 맞춤형 게임 추천, 장르별 게임 추천, 마이페이지
- 신승오 : 전체 CSS, 메인 페이지
- 장수현 : 커뮤니티 전반, 로그인, 푸터
- 정수훈 : 헤더, 회원가입, 회원정보 관리, 커뮤니티
- 진현진 : 로그인/회원가입, 결과 저장 및 공유, 타이틀 및 파비콘

### Backend
- 김현태 : 커뮤니티 게시글 수정 및 삭제
- 신승오 : 스팀 api를 활용한 게임DB 및 데이터 추출, 게시글·이미지 업로드
- 장수현 : 일반 로그인 및 소셜 로그인, 내가 쓴 글, 보안
- 정수훈 : 회원가입 및 유효성 검사
- 진현진 : 테스트 결과 도출과 저장

## 실행 화면
### 메인페이지
<img width="1920" height="945" alt="Image" src="https://github.com/user-attachments/assets/897f0901-191a-414b-890e-42d6f9f28ea1" />

### 테스트 페이지
<img width="1920" height="945" alt="Image" src="https://github.com/user-attachments/assets/bb170613-c773-4f99-bb18-f8a69dc75236" />

### 테스트 결과 페이지
<img width="1920" height="945" alt="Image" src="https://github.com/user-attachments/assets/3f96c208-6697-45ec-adad-2083aaac5331" />

### 맞춤형 게임 추천
<img width="1920" height="945" alt="Image" src="https://github.com/user-attachments/assets/ef13736d-5a14-47c7-81b1-ed47f483d494" />

### 커뮤니티
<img width="1920" height="945" alt="Image" src="https://github.com/user-attachments/assets/bef92cb4-15bc-4e13-90a1-19189058d32a" />

## 리액트 깃허브 주소
https://github.com/jjop2/team3-project-react.git