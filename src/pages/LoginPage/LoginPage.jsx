import useInput from "hooks/useInput";
import * as S from "./LoginPage.style";

const LoginPage = () => {
  const [idInput, setIdInput, idHandler] = useInput("");
  const [passwordInput, setPasswordInput, passwordHandler] = useInput("");

  const submitSignUp = () => {
    const submitform = {
      idInput,
      passwordInput,
    };
    console.log(submitform);
  };

  return (
    <S.LoginPageWrapper>
      <S.LoginText> </S.LoginText>
      <S.InputWrapper>
        <S.InputBox placeholder="이메일" type="email" onChange={idHandler} />
        <S.InputBox placeholder="비밀번호" type="password" onChange={passwordHandler} />
      </S.InputWrapper>
      <S.NextButton onClick={submitSignUp}>로그인</S.NextButton>
    </S.LoginPageWrapper>
  );
};
export default LoginPage;
