import { useNavigate } from "react-router-dom";
import * as S from "./MainPage.style";
import GroupList from "components/group/GroupList/GroupList";
import { PATH } from "constants/path";
import { useGroupListQuery } from "hooks/apiHook/useGroupListQuery";

const MainPage = () => {
  const { groupListData } = useGroupListQuery();
  const navigate = useNavigate();

  return (
    <S.MainPageWrapper>
      <S.LabelWrapper>나의 모임 통장</S.LabelWrapper>
      <GroupList groupList={groupListData?.data} />
      <S.CreateGroupButton onClick={() => navigate(PATH.REGIST_GROUP_PAGE)}>+</S.CreateGroupButton>
    </S.MainPageWrapper>
  );
};
export default MainPage;
