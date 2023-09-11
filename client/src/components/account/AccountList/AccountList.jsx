import AccountItem from "components/account/AccountItem/AccountItem";
import * as S from "components/account/AccountList/AccountList.style";
import { useEffect, useState } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const AccountList = ({ accountList, openModal }) => {
  const [filteredAccount, setFilteredAccount] = useState();

  const settings = {
    dots: true,
    lazyLoad: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    initialSlide: 2,
  };

  useEffect(() => {
    const newData = accountList.filter((item) => !item.representedAccount);
    setFilteredAccount(newData);
  }, [accountList]);

  return (
    <S.AccountListWrapper>
      <Slider {...settings}>
        {filteredAccount?.map((group) => (
          <AccountItem
            key={group.accountId}
            accountId={group.accountId}
            balance={group.balance}
            openModal={openModal}
          />
        ))}
      </Slider>
    </S.AccountListWrapper>
  );
};

export default AccountList;
