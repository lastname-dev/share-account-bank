import AccountItem from "components/account/AccountItem/AccountItem";
import * as S from "components/account/AccountList/AccountList.style";

const AccountList = ({ accountList }) => {
  console.log(accountList);
  return (
    <S.AccountListWrapper>
      {accountList.map((group) => (
        <AccountItem key={group.accountId} accountId={group.accountId} balance={group.balance} />
      ))}
    </S.AccountListWrapper>
  );
};

export default AccountList;
