import ProgressBar from "components/@common/ProgressBar/ProgressBar";
import * as S from "./GroupItem.style";
import { useNavigate } from "react-router-dom";
import { PATH } from "constants/path";

const GroupItem = ({ group }) => {
  const navigate = useNavigate();

  const handleNavigation = (isPaid, account) => {
    if (isPaid) {
      navigate(PATH.TRAVEL_INFO_PAGE(account), { state: { account } });
      return;
    }
    navigate(PATH.DEPOSIT_PAGE(account), { state: { account: group.account, dues: group.dues } });
  };

  return (
    <S.GroupItemWrapper onClick={() => navigate(PATH.GROUP_PAGE(group.account))}>
      <S.GroupItemNameContainer>
        <S.GroupItemName>{group.groupName}</S.GroupItemName>
      </S.GroupItemNameContainer>
      <S.DepositContainer>
        <S.GroupItemNumber>{group.account}</S.GroupItemNumber>
        <S.Deposit>{group.balance}원</S.Deposit>
        <ProgressBar goalMoney={group.goal} currentMoney={group.balance} />
      </S.DepositContainer>
    </S.GroupItemWrapper>
  );
};

export default GroupItem;
