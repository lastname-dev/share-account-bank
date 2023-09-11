import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { MdCreditCard } from "react-icons/md";
import * as S from "./MainPage.style";
import GroupList from "components/group/GroupList/GroupList";
import { PATH } from "constants/path";
import { useGroupListQuery } from "hooks/apiHook/useGroupListQuery";
import { useAccountListQuery } from "hooks/apiHook/useAccountListQuery";
import AccountList from "components/account/AccountList/AccountList";
import Modal from "components/@common/Modal/Modal";
import useModal from "hooks/useModal";
import { selectedMyAccountState } from "recoil/atoms";
import DepositModal from "components/account/DepositModal/DepositModal";
import { useEffect, useState } from "react";
import AccountItem from "components/account/AccountItem/AccountItem";
import MainAccountModal from "components/account/MainAccountModal/MainAccountModal";
import React, { Component } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const MainPage = () => {
  const navigate = useNavigate();
  const { groupListData } = useGroupListQuery();
  const { accountListData } = useAccountListQuery();
  const { openModal: openDepositModal, closeModal: closeDepositModal } = useModal("deposit");
  const { openModal: openCreateAccountModal, closeModal: closeCreateAccountModal } = useModal("createMainAccount");
  const selectedMyAccount = useRecoilValue(selectedMyAccountState);
  const [mainAccount, setMainAccount] = useState({});
  const [isAccountListVisible, setIsAccountListVisible] = useState(true);

  const settings = {
    dots: true,
    lazyLoad: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    initialSlide: 2,
  };

  const findMainAccount = (responseData) => {
    const findedData = responseData?.filter((item) => item.representedAccount);
    return findedData;
  };

  useEffect(() => {
    setMainAccount(findMainAccount(accountListData.data)[0]);
  }, [accountListData]);

  const toggleView = () => {
    setIsAccountListVisible(!isAccountListVisible);
  };

  return (
    <>
      <button onClick={toggleView}>Toggle View</button>
      <Slider {...settings}>
        {mainAccount?.accountId ? (
          <AccountItem
            key={mainAccount?.accountId}
            accountId={mainAccount?.accountId}
            balance={mainAccount?.balance}
            openModal={openDepositModal}
          />
        ) : (
          <S.CreateMainAccountContainer>
            <h3>주계좌가 없습니다.</h3>
            <S.CreateMainAccountButtonContainer onClick={openCreateAccountModal}>
              <S.CreateMainAccountButton>주계좌 설정하러가기</S.CreateMainAccountButton>
              <MdCreditCard />
            </S.CreateMainAccountButtonContainer>
          </S.CreateMainAccountContainer>
        )}

        {isAccountListVisible
          ? accountListData?.data?.map((account) => (
              <AccountItem
                key={account.accountId}
                accountId={account.accountId}
                balance={account.balance}
                openModal={openDepositModal}
              />
            ))
          : groupListData?.data?.map((group) => (
              <GroupList
                key={group.id}
                groupList={groupListData?.data} /* Add any necessary props for GroupList component */
              />
            ))}
        {/* <S.CreateGroupButton onClick={() => navigate(PATH.REGIST_GROUP_PAGE)}>+</S.CreateGroupButton> */}
      </Slider>

      <Modal id="deposit">
        <DepositModal selectedMyAccount={selectedMyAccount} closeModal={closeDepositModal} />
      </Modal>
      <Modal id="createMainAccount">
        <MainAccountModal accountList={accountListData?.data} closeModal={closeCreateAccountModal} />
      </Modal>
    </>
  );
};
export default MainPage;
