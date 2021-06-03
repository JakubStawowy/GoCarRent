import {makeStyles} from "@material-ui/core";

export const useAnnouncementStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
        height: '100%',
        flexDirection: 'column',
        justifyContent: 'flex-start',
        alignItems: 'center'
    },
    header: {
        padding: '1em'
    },
    content: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        padding: '2em',
        width: '90%',
        borderRadius: '2em',
        display: 'flex',
        alignItems: 'center'
    },
    image: {
        width: '15vw',
        height: '15vw',
        objectFit: 'cover',
        borderRadius: '2em'
    },
    navLink: {
        textDecoration: 'none',
        color: 'black'
    },
    data: {
        background: 'white',
        borderRadius: '2em'
    }
}));
