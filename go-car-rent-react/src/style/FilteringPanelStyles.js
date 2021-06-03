import {makeStyles} from "@material-ui/core";

export const useFilteringPanelStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexDirection: 'column',
        height: '100%',
        justifyContent: 'space-around'
    },
    form: {
        height: '70%',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
    },
    item: {
        width: '90%',
        marginBottom: '1em',
    },
    gridContainer: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        borderRadius: '2em',
        padding: '2em',
    },
    buttonGridItem: {
        display: 'flex',
        justifyContent: 'center',
    }
}));
