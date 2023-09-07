import * as S from "./AccountItem.style";

const AccountItem = ({ accountId, balance }) => {
  return (
    <S.AccountItemWrapper>
      <S.DepositContainer>
        <S.AccountItemNumber>{accountId}</S.AccountItemNumber>
        <S.Deposit>{balance}원</S.Deposit>
      </S.DepositContainer>
      <S.AccountButtonContainer>
        <S.DetailButton>이체</S.DetailButton>
        <S.DetailButton>조회</S.DetailButton>
      </S.AccountButtonContainer>
    </S.AccountItemWrapper>
  );
};

export default AccountItem;
