import GroupDetail from "components/group/GroupDetail/GroupDetail";
import * as S from "./GroupPage.style";
import GroupAccountDetail from "components/group/GroupAccountDetail/GroupAccountDetail";
import { useNavigate, useParams } from "react-router-dom";
import { useGroupQuery } from "hooks/apiHook/useGroupQuery";
import { PATH } from "constants/path";

const GroupPage = () => {
  const navigate = useNavigate();
  const { groupId } = useParams();
  const { groupData } = useGroupQuery(groupId);

  const handleNavigate = () => {
    navigate(PATH.INVITATION_PAGE(groupId));
  };

  return (
    <>
      <S.GroupPageWrapper>
        <GroupAccountDetail
          groupName={groupData.data.groupName}
          account={groupData.data.account}
          goal={groupData.data.goal}
          balance={groupData.data.balance}
        />
        <GroupDetail
          goal={groupData.data.goal}
          dues={groupData.data.dues}
          duesDate={groupData.data.duesDate}
          startDate={groupData.data.startDate}
          limitMember={groupData.data.limitMember}
          participants={groupData.data.limitMember}
        />
        <S.GroupInviteButton onClick={handleNavigate}>친구 초대하기</S.GroupInviteButton>
      </S.GroupPageWrapper>
    </>
  );
};

export default GroupPage;
