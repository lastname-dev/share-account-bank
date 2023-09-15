import * as S from "./GroupJoinPage.style";
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import businessAPI from "apis/business";
import InviteTicket from "components/invite/InviteTicket";
const GroupJoinPage = () => {
  const location = useLocation();
  const link = location.pathname.split("/joingroup/")[1];

  const [groupName, setGroupName] = useState("");
  const [GroupId, setGroupId] = useState(1);
  const [showModal, setShowModal] = useState(false);
  const [group, setGroup] = useState();
  useEffect(() => {
    const groupName = async () => {
      try {
        console.log(link);
        const response = await businessAPI.enterJoinLink(link);
        setGroupName(response.data.groupName);
        setGroupId(response.data.groupId);
        setGroup(response.data);
      } catch (error) {
        console.error("초대장 출처 확인 :", error);
      }
    };
    if (link.length > 1) {
      groupName();
    }
  }, [link]);

  const joinGroup = async () => {
    try {
      const response = await businessAPI.joinGroup(groupName, link);
    } catch (error) {
      console.error("가입 오류:", error);
    }
  };

  return (
    <>{group && <InviteTicket group={group} />}</>
    // <S.GroupJoinPageWrapper>
    // //   <S.GroupJoinMessage>
    // //     <br /> <h3>{GroupName} 초대장</h3>
    // //   </S.GroupJoinMessage>
    // //   <S.GroupJoinImageContainer></S.GroupJoinImageContainer>
    //   <S.GroupJoinButton onClick={() => joinGroup()}>수락</S.GroupJoinButton>
    // // </S.GroupJoinPageWrapper>
  );
};

export default GroupJoinPage;
