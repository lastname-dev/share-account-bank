import { useNavigate } from "react-router-dom";
import * as S from "./MainPage.style";
import GroupList from "components/group/GroupList/GroupList";
import { PATH } from "constants/path";
import { useGroupListQuery } from "hooks/apiHook/useGroupListQuery";
import { useAccountListQuery } from "hooks/apiHook/useAccountListQuery";
import AccountList from "components/account/AccountList/AccountList";
import Modal from "components/@common/Modal/Modal";
import useModal from "hooks/useModal";

const MainPage = () => {
  const { groupListData } = useGroupListQuery();
  const { accountListData } = useAccountListQuery();
  const navigate = useNavigate();
  const { openModal } = useModal("accountId");

  return (
    <>
      <S.MainPageWrapper>
        <S.LabelWrapper>내 계좌</S.LabelWrapper>
        <AccountList accountList={accountListData?.data} openModal={openModal} />
        <S.LabelWrapper>내 모임 계좌</S.LabelWrapper>
        <GroupList groupList={groupListData?.data} />
        <S.CreateGroupButton onClick={() => navigate(PATH.REGIST_GROUP_PAGE)}>+</S.CreateGroupButton>
        <Modal id={"accountId"}>
          <h1>"accountId"</h1>
        </Modal>
      </S.MainPageWrapper>
    </>
  );
};
export default MainPage;
