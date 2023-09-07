import GroupItem from "components/group/GroupItem/GroupItem";
import { GroupListWrapper } from "components/group/GroupList/GroupList.style";

const GroupList = ({ groupList }) => {
  return (
    <GroupListWrapper>
      {groupList.map((group) => (
        <GroupItem key={group.account} group={group} />
      ))}
    </GroupListWrapper>
  );
};

export default GroupList;
