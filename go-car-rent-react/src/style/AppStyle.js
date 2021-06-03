import {makeStyles} from "@material-ui/core";

export const useAppStyles = makeStyles((theme) => ({
    main: {
        [theme.breakpoints.down('xs')]: {
            maxWidth: '100%',
            flexBasis: '100%'
        },
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        maxHeight: '100vh',
        overflow: 'auto'
    },
    subContainer: {
        flex: '16',
    },
    logo: {
        position: 'fixed',
        width: '50vw',
        left: '50%',
        marginLeft: '-25%',
        opacity: 0.3,
        zIndex: -1
    }
}));