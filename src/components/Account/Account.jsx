import * as S from "./Account.style";

const Account = ({ accountName, accountNumber, deposit }) => {
  return (
    <S.AccountWrapper>
      <S.AccountNameContainer>
        <S.AccountName>{accountName}</S.AccountName>
      </S.AccountNameContainer>
      <S.DepositContainer>
        <S.AccountNumber>{accountNumber}</S.AccountNumber>
        <S.Deposit>{deposit}</S.Deposit>
      </S.DepositContainer>
    </S.AccountWrapper>
  );
};

export default Account;
