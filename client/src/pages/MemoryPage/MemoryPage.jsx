import businessAPI from "apis/business";
import { useEffect, useState } from "react";
import * as S from "./MemoryPage.style";

const MemoryPage = () => {
  const [memoryData, setMemoryData] = useState([]);

  useEffect(() => {
    const fetchMemories = async () => {
      try {
        const response = await businessAPI.getMemories();
        const travel = [
          { groupName: "group1", startDate: "2023-03-03", endDate: "2023-05-05" },
          { groupName: "group2", startDate: "2023-03-03", endDate: "2023-05-05" },
        ];
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
      {memoryData.map((memory, index) => (
        <S.MemoryItem key={index}>
          <h3>{memory.groupName}</h3>
          {memory.startDate}~ {memory.endDate}
        </S.MemoryItem>
      ))}
    </div>
  );
};

export default MemoryPage;
