import {makeStyles} from "@material-ui/core";

export const useSideBarStyles = makeStyles((theme)=>({
    sideBar: {
        [theme.breakpoints.down('xs')]: {
            display: 'none'
        }
    }
}));