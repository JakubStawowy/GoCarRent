import {makeStyles} from "@material-ui/core";

export const useFeedbackStyles = makeStyles((theme) => ({
    paper: {
        // height: '15vh',
        width: '100%',
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'flex-start',
        padding: '1em'
    },
    img: {
        width: '10vh',
        height: '10vh'
    },
    content: {
        height: '100%',
        padding: '0',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around',
    },
    p: {
        padding: 0
    },
    feedbackTop: {
        display: 'flex',
        justifyContent: 'space-between'
    },
    stars: {
        color: '#4BBEBAE0',
    },
    feedbackAuthorLabel: {

    }
}));
