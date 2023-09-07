import { useLocation } from "react-router-dom";
import * as S from "./DepositPage.style";
import { useTransactionMutation } from "hooks/apiHook/useTransactionMutation";
import { useState } from "react";
import { useAccountListQuery } from "hooks/apiHook/useAccountListQuery";

const DepositPage = () => {
  const CLICK_MESSAGE = "가방을 클릭해 회비를 모아주세요!";
  const DEPOSIT_MESSAGE = "출금 계좌를 선택해주세요!";
  const [confirm, setConfirm] = useState(false);
  const [pickedAccount, setPickedAccount] = useState("");
  const { accountListData } = useAccountListQuery();
  const transactMutation = useTransactionMutation();
  const location = useLocation();
  // const account = location.state.account;
  // const dues = location.state.dues;

  // const transactMoney = () => {
  //   transactMutation.mutate({ sender: pickedAccount, receiver: account, amount: dues });
  // };

  return (
    <S.DepositPageWrapper>
      <S.DepositMessage>{confirm ? DEPOSIT_MESSAGE : CLICK_MESSAGE}</S.DepositMessage>
      <S.DepositImageContainer>
        {confirm ? (
          <S.SelectAccountBox>
            <S.AccountSelect
              name="account"
              onChange={(e) => {
                setPickedAccount(e.target.value);
              }}
            >
              <option value={""}>내 계좌</option>
              {accountListData?.data.map((account) => (
                <option key={account.accountId} value={account.accountId}>
                  {account.accountId}
                </option>
              ))}
            </S.AccountSelect>
          </S.SelectAccountBox>
        ) : (
          <S.DepositImage src={process.env.PUBLIC_URL + "/image/bag.png"} alt="logo" onClick={() => setConfirm(true)} />
        )}
      </S.DepositImageContainer>
      {confirm && <S.DepositButton>모임통장 보러가기</S.DepositButton>}
    </S.DepositPageWrapper>
  );
};

export default DepositPage;
