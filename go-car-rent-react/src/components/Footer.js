import React from 'react';
import {Button, Grid} from "@material-ui/core";
import MenuIcon from '@material-ui/icons/Menu';
import {useFooterStyles} from "../style/FooterStyles";

export default function Footer(props) {
    const classes = useFooterStyles();
    return (
        <Grid item xs className={classes.root}>
            <Button className={classes.button} onClick={()=>props.action()}>
                <MenuIcon fontSize={"large"}/>
            </Button>
        </Grid>
    );
};