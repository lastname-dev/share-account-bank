import { useNavigate } from "react-router-dom";
import * as S from "./MainPage.style";
import GroupList from "components/group/GroupList/GroupList";
import { PATH } from "constants/path";
import { useGroupListQuery } from "hooks/apiHook/useGroupListQuery";
import { useAccountListQuery } from "hooks/apiHook/useAccountListQuery";
import AccountList from "components/account/AccountList/AccountList";

const MainPage = () => {
  const { groupListData } = useGroupListQuery();
  const { accountListData } = useAccountListQuery();
  const navigate = useNavigate();

  return (
    <S.MainPageWrapper>
      <S.LabelWrapper>내 계좌</S.LabelWrapper>
      <AccountList accountList={accountListData?.data} />
      <S.LabelWrapper>나의 모임 통장</S.LabelWrapper>
      <GroupList groupList={groupListData?.data} />
      <S.CreateGroupButton onClick={() => navigate(PATH.REGIST_GROUP_PAGE)}>+</S.CreateGroupButton>
    </S.MainPageWrapper>
  );
};
export default MainPage;
