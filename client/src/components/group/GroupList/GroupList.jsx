import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import GroupItem from "components/group/GroupItem/GroupItem";
import { GroupListWrapper } from "components/group/GroupList/GroupList.style";

const GroupList = ({ groupList }) => {
  const sliderSetting = {
    dots: true,
    lazyLoad: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
  };
  return (
    <GroupListWrapper>
      <Slider {...sliderSetting}>
        {groupList.map((group) => (
          <GroupItem key={group.account} group={group} />
        ))}
      </Slider>
    </GroupListWrapper>
  );
};

export default GroupList;
