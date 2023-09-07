import ProgressBar from "components/@common/ProgressBar/ProgressBar";
import * as S from "./GroupAccountDetail.style";

const GroupAccountDetail = ({ groupName, account, goal, balance, money }) => {
  return (
    <S.GroupAccountDetailWrapper>
      <S.InfoContainer>
        <S.Name>{groupName}</S.Name>
        <S.Number>{account}</S.Number>
      </S.InfoContainer>
      <S.MoneyContainer>
        <S.Money>{balance}원</S.Money>
        <select name="국가">
          <option value="USD">USD</option>
          <option value="KOW">KOW</option>
          <option value="JPY">JPY</option>
        </select>
      </S.MoneyContainer>
      <ProgressBar goalMoney={goal} currentMoney={balance} />
      <S.GroupAccountButtonContainer>
        <S.GroupAccountButton>여행가기</S.GroupAccountButton>
        <S.GroupAccountButton>환전 우대율 조회</S.GroupAccountButton>
      </S.GroupAccountButtonContainer>
    </S.GroupAccountDetailWrapper>
  );
};

export default GroupAccountDetail;