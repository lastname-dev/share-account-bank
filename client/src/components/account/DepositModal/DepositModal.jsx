import LabelInput from "components/@common/LabelInput/LabelInput";
import * as S from "components/account/DepositModal/DepositModal.style";
import useInput from "hooks/useInput";

const DepositModal = ({ selectedMyAccount }) => {
  const [targetAccount, setTargetAccount, targetAccountHandler] = useInput();

  return (
    <S.DepositModalWrapper>
      <S.CreateAccountModalTitle>이체</S.CreateAccountModalTitle>
      <LabelInput labelTitle="송금 계좌" inputType="text" handler={targetAccountHandler} />
      <S.ButtonContainer>
        <S.CancelButton>취소</S.CancelButton>
        <S.CreateButton>송금</S.CreateButton>
      </S.ButtonContainer>
    </S.DepositModalWrapper>
  );
};
export default DepositModal;

// 예금주 실명 조회
