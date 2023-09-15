import * as S from "./GroupJoinPage.style";
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import businessAPI from "apis/business";
import InviteTicket from "components/invite/InviteTicket";
const GroupJoinPage = () => {
  const link = useLocation().pathname.split("/joinGroup/")[1]; // 현재 url path에서 /invite/를 빼고 groupName을 추출
  console.log(link);
  const [groupName, setGroupName] = useState("");
  const [GroupId, setGroupId] = useState(1);
  const [showModal, setShowModal] = useState(false);
  const [group, setGroup] = useState();

  useEffect(() => {
    const groupName = async () => {
      try {
        const response = await businessAPI.enterJoinLink(link);
        console.log(response.data);
        setGroupName(response.data.groupName);
        setGroupId(response.data.groupId);
        setGroup(response.data);
      } catch (error) {
        console.error("초대장 출처 확인 :", error);
      }
    };
    groupName();
  }, []);

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
