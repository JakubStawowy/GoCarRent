import React, {Component} from 'react';
import '../components/components.css';
import {Container, Grid, makeStyles, Typography} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
}));

export default function UserCars() {
    const classes = useStyles();
    return (
        <Grid item xs={8}>
            <Container className={classes.container}>
                <Typography component="div" className={classes.home}>
                    UserCars
                </Typography>
            </Container>
        </Grid>
    );
}