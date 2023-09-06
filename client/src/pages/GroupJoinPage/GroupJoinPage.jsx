import * as S from "./GroupJoinPage.style";
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import businessAPI from "apis/business";

const GroupJoinPage = () => {
  const link = useLocation().pathname.split("/joinGroup/")[1]; // 현재 url path에서 /invite/를 빼고 groupName을 추출

  const [GroupName, setGroupName] = useState("");
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    const groupName = async () => {
      try {
        const response = await businessAPI.enterJoinLink(link);
        console.log(response.data);
        setGroupName(response.data.groupName);
      } catch (error) {
        console.error("초대 링크 생성 오류:", error);
      }
    };
    groupName();
  }, []);
  return (
    <S.GroupJoinPageWrapper>
      <S.GroupJoinMessage>
        <br /> {GroupName}에 초대 받으셨습니다 !
      </S.GroupJoinMessage>
      <S.GroupJoinImageContainer>
        <S.GroupJoinImage
          src={process.env.PUBLIC_URL + "/image/link.png"}
          alt="logo"
        />
      </S.GroupJoinImageContainer>
      <S.GroupJoinInfoText>클릭해서 초대링크 생성</S.GroupJoinInfoText>
      <S.GroupJoinButton>완료</S.GroupJoinButton>
      {/* <S.ModalOverlay $show={showModal} onClick={() => setShowModal(false)} /> */}
      {/* <S.ModalContainer $show={showModal}></S.ModalContainer> */}
    </S.GroupJoinPageWrapper>
  );
};

export default GroupJoinPage;
