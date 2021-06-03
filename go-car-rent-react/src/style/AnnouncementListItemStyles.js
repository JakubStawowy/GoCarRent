import {makeStyles} from "@material-ui/core";

export const useAnnouncementListItemStyles = makeStyles((theme) => ({
    paper: {
        // height: '40vh',
        width: '100%',
        display: 'flex',
        flexDirection: 'row'
    },
    img: {
        height: '90%',
        flex: '3'
    },
    content: {
        height: '100%',
        flex: '7',
        padding: '0',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around'
    },
    p: {
        padding: 0
    },
    cancelButton: {
        background: '#FA8072'
    },
    blockButton: {
        background: '#4BBEBAE0'
    },
    image: {
        width: '15vw',
        height: '15vw',
        objectFit: 'cover'
    },
    iconButton: {
        [theme.breakpoints.down('xs')]: {
            // fontSize: "large"
            width: '0.5em'
        }
    },
    navLink: {
        textDecoration: 'none',
        color: 'black'
    },
    title: {
        overflow: 'hidden',
        
    }
}));