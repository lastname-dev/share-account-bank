import { MdArrowBackIosNew, MdAssistantPhoto } from "react-icons/md";
import { useLocation, useNavigate } from "react-router-dom";
import * as S from "./Header.style";
import { theme } from "styles/theme";
import { PATH } from "constants/path";

const Header = () => {
  const { pathname } = useLocation();
  const navigate = useNavigate();

  if (pathname === PATH.ROOT)
    return (
      <S.HeaderWrapper $isMain={pathname === PATH.ROOT}>
        <MdAssistantPhoto size={"3rem"} color={theme.color.darkgray} onClick={() => navigate(PATH.TRAVEL_LOG_PAGE)} />
      </S.HeaderWrapper>
    );

  return (
    <S.HeaderWrapper $isMain={pathname === PATH.ROOT}>
      <S.BackButtonContainer onClick={() => navigate(-1)}>
        <MdArrowBackIosNew size={"2rem"} color={theme.color.darkgray} />
      </S.BackButtonContainer>
    </S.HeaderWrapper>
  );
};
export default Header;
