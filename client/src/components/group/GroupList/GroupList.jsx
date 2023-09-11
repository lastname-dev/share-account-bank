import GroupItem from "components/group/GroupItem/GroupItem";
import { GroupListWrapper } from "components/group/GroupList/GroupList.style";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
const GroupList = ({ groupList }) => {
  const settings = {
    dots: true,
    lazyLoad: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    initialSlide: 2,
  };
  return (
    <div>
      <Slider {...settings}>
        {groupList.map((group) => (
          <GroupItem key={group.account} group={group} />
        ))}
      </Slider>
    </div>
  );
};

export default GroupList;
