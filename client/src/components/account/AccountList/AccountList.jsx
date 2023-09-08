import AccountItem from "components/account/AccountItem/AccountItem";
import * as S from "components/account/AccountList/AccountList.style";

const AccountList = ({ accountList, openModal }) => {
  return (
    <S.AccountListWrapper>
      {accountList.map((group) => (
        <AccountItem key={group.accountId} accountId={group.accountId} balance={group.balance} openModal={openModal} />
      ))}
    </S.AccountListWrapper>
  );
};

export default AccountList;
