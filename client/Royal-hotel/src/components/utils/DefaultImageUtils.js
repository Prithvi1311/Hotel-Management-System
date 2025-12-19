import defaultImage from "../../images/room7.jpg";
import singleRoomImage from "../../images/single_room.png";
import doubleRoomImage from "../../images/double_room.png";
import familyRoomImage from "../../images/family_room.png";

export const getDefaultImage = (roomType) => {
    if (!roomType) return defaultImage;
    const type = roomType.toLowerCase();
    if (type.includes("single")) {
        return singleRoomImage;
    } else if (type.includes("double")) {
        return doubleRoomImage;
    } else if (type.includes("family")) {
        return familyRoomImage;
    } else {
        return defaultImage;
    }
};
