import React, {Component} from 'react';
import '../components/components.css';
import {Button, Container, Fab, FormControl, Grid, Input, makeStyles, Typography} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    container: {
        height: '100%',
        width: '100%',
        // marginTop: '2em',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-around'
    },
    form: {
        width: '100%',
        height: '70%',
        display: 'flex',
        flexDirection: 'column',
    },
    item: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        flexBasis: '25%'
    },
    input: {
        height: '25%',
        width: '60%'
    },
    button: {
        background: '#4BBEBAE0',
        width: '40%',
    },
    gridContainer: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '80%',
        borderRadius: '2em'
    },
}));

export default function Settings() {
    const classes = useStyles();
    return (
        <Container className={classes.container}>
            <Typography variant={'h4'} align={'center'}>
                User settings
            </Typography>
            <FormControl className={classes.form}>
                <Grid container className={`${classes.container} ${classes.gridContainer}`}>
                    <Grid item xs={6} className={classes.item}>
                        <Input type={'text'} className={classes.input} placeholder={"name"}/>
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input type={'text'} className={classes.input} placeholder={"surname"}/>
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input type={'text'} className={classes.input} placeholder={"password"}/>
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input type={'text'} className={classes.input} placeholder={"confirm password"}/>
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input type={'text'} className={classes.input} placeholder={"phone"}/>
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input type={'text'} className={classes.input} placeholder={"surname"}/>
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input type={'file'} className={classes.input}/>
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Fab variant={'extended'} className={classes.button}>
                            Change
                        </Fab>
                    </Grid>
                </Grid>
            </FormControl>
        </Container>
    );
}