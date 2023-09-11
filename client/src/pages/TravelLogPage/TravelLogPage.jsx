import * as S from "pages/TravelLogPage/TravelLogPage.style";
import businessAPI from "apis/business";
import { useEffect, useState } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "./TravelLogPage.css";

const TravelLogPage = () => {
  const [memoryData, setMemoryData] = useState([]);
  const [image, setImage] = useState();
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
    const fetchMemories = async () => {
      try {
        const response = await businessAPI.getTravelLogs();
        const travel = [
          { groupName: "group1", startDate: "2023-03-03", endDate: "2023-05-05" },
          { groupName: "group2", startDate: "2023-03-03", endDate: "2023-05-05" },
        ];
        setImage(response.data[0].photo);
        setMemoryData(travel);
      } catch (e) {
        console.error(e);
      }
    };

    fetchMemories();
  }, []);

  return (
    <div>
      <h1>여행 기록</h1>
      <Slider {...settings}>
        {memoryData.map((memory, index) => (
          <S.MemoryItem key={index}>
            <h3>{memory.groupName}</h3>
            <div className="centered-content">
              <img src={image} alt={memory.groupName} />
              <p>
                {memory.startDate} ~ {memory.endDate}
              </p>
            </div>
          </S.MemoryItem>
        ))}
      </Slider>
    </div>
  );
};

export default TravelLogPage;
