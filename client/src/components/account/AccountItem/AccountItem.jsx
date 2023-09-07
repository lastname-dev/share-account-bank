import { useNavigate } from "react-router-dom";
import * as S from "./AccountItem.style";
import { PATH } from "constants/path";

const AccountItem = ({ accountId, balance, openModal }) => {
  const navigate = useNavigate();

  return (
    <>
      <S.AccountItemWrapper>
        <S.DepositContainer>
          <S.AccountItemNumber>{accountId}</S.AccountItemNumber>
          <S.Deposit>{balance}원</S.Deposit>
        </S.DepositContainer>
        <S.AccountButtonContainer>
          <S.DetailButton onClick={openModal}>이체</S.DetailButton>
          <S.DetailButton onClick={() => navigate(PATH.ACCOUNT_PAGE(accountId))}>조회</S.DetailButton>
        </S.AccountButtonContainer>
      </S.AccountItemWrapper>
    </>
  );
};

export default AccountItem;
