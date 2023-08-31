import * as S from "./IntroPage.style";

const IntroPage = () => {
  return (
    <S.IntroPageWrapper>
      <S.LogoContainer>
        <S.LogoImage src={process.env.PUBLIC_URL + "/image/logo.png"} alt="logo" />
      </S.LogoContainer>
      <S.ButtonWrapper>
        <S.LoginButton>로그인</S.LoginButton>
        <S.SigninButton>회원가입</S.SigninButton>
      </S.ButtonWrapper>
    </S.IntroPageWrapper>
  );
};
export default IntroPage;
