import React, { useEffect } from "react";

const StoreMap = ({ x, y, text, onClose }) => {
  useEffect(() => {
    const { kakao } = window;
    const mapContainer = document.getElementById("modalMap");
    var markerPosition = new kakao.maps.LatLng(y, x);

    var marker = {
      position: markerPosition,
      text: text,
    };
    const mapOption = {
      center: new kakao.maps.LatLng(y, x),
      level: 3,
      marker: marker,
    };

    const map = new kakao.maps.StaticMap(mapContainer, mapOption);

    return;
  }, [x, y]);

  return (
    <div className="modal">
      <div className="modal-content">
        <div id="modalMap" style={{ width: "100%", height: "300px" }}></div>
        <button onClick={onClose}>닫기</button>
      </div>
    </div>
  );
};

export default StoreMap;
