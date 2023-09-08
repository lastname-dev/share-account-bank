import { useState, useEffect } from "react";
import StoreList from "components/exchangeStore/StoreList/StoreList";
import StoreMap from "components/exchangeStore/Map/StoreMap";
import { ModalContainer, ModalContent, VisibleModal } from "components/exchangeStore/Map/StoreMap.style";
import SearchBar from "components/exchangeStore/SearchBar/SearchBar";
const ExchangeMoneyStorePage = () => {
  const [info, setInfo] = useState();
  const [lat, setLat] = useState(0);
  const [lng, setLng] = useState(0);
  const [location, setLocation] = useState();
  const [stores, setStores] = useState([]);
  const [modalVisible, setModalVisible] = useState(false);
  const [where, setWhere] = useState();

  const search = (word) => {
    const { kakao } = window;
    const ps = new kakao.maps.services.Places();

    ps.keywordSearch(word + " 신한은행", (result, status, _pagination) => {
      const newStores = [];

      for (var i = 0; i < result.length; i++) {
        newStores.push(result[i]);
      }
      console.log(word + " 신한은행");
      setStores(newStores);
      console.log(newStores);
      return newStores;
    });
  };

  const handleListItemClick = (store) => {
    setModalVisible(true);
    setInfo(store);
  };

  const closeModal = () => {
    // 모달을 닫는 함수
    setModalVisible(false);
  };
  const handleLocationChange = (event) => {
    setWhere(event.target.value);
  };
  const handleSearchIconClick = () => {
    // setLocation(where);
    search(where);
  };

  useEffect(() => {
    const { kakao } = window;

    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const { latitude, longitude } = position.coords;
          setLat(latitude);
          setLng(longitude);
        },
        (error) => {
          console.error("Error getting user location:", error);
        },
      );
    } else {
      console.log("Geolocation is not available in this browser.");
    }

    var geocoder = new kakao.maps.services.Geocoder();

    geocoder.coord2Address(lng, lat, async (e) => {
      setLocation(e[0]?.address.region_3depth_name);
    });
  });

  useEffect(() => {
    const { kakao } = window;
    const ps = new kakao.maps.services.Places();

    ps.keywordSearch(location + " 신한은행", (result, status, _pagination) => {
      const newStores = [...stores];

      for (var i = 0; i < result.length; i++) {
        newStores.push(result[i]);
      }
      console.log(location + " 신한은행");
      setStores(newStores);
    });
  }, [location]);

  return (
    <div>
      <SearchBar value={where} onChange={handleLocationChange} onSearch={handleSearchIconClick}></SearchBar>
      <StoreList stores={stores} onListItemClick={handleListItemClick} />{" "}
      {modalVisible ? (
        <VisibleModal>
          <ModalContent>
            {modalVisible && <StoreMap x={info.x} y={info.y} text={info.place_name} onClose={closeModal} />}
          </ModalContent>
        </VisibleModal>
      ) : null}
    </div>
  );
};

export default ExchangeMoneyStorePage;
