import * as S from "./InvitationPage.style";

const InvitationPage = () => {
  return (
    <S.InvitationPageWrapper>
      <S.InvitationMessage>
        초대링크를 생성해
        <br /> 친구를 초대하세요!
      </S.InvitationMessage>
      <S.InvitationImageContainer>
        <S.InvitationImage
          src={process.env.PUBLIC_URL + "/image/link.png"}
          alt="logo"
        />
      </S.InvitationImageContainer>
      <S.InvitationInfoText>클릭해서 초대링크 생성</S.InvitationInfoText>
      <S.InvitationButton>완료</S.InvitationButton>
    </S.InvitationPageWrapper>
  );
};

export default InvitationPage;
