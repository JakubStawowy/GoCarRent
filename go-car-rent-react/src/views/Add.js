import React from 'react';
import '../components/components.css';
import {
    Button,
    Card,
    Container, Fab,
    FormControl, Grid,
    makeStyles,
    MenuItem,
    Select, TextareaAutosize,
    TextField,
    Typography
} from "@material-ui/core";
import PublishIcon from '@material-ui/icons/Publish';
import RoomIcon from '@material-ui/icons/Room';
import carBrands from "../data/carBrands";
import ImageIcon from '@material-ui/icons/Image';

const useStyles = makeStyles((theme) => ({
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
        width: '90%'
    },
    gridContainer: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '80%',
        borderRadius: '2em'
    },
    button: {
        background: '#4BBEBAE0',
    },
    titleField: {
        marginTop: '2em'
    }
}));

export default function Add() {
    const classes = useStyles();
    return (
        <Container className={classes.container}>
            <Typography variant={'h4'} align={'center'}>
                Add announcement
            </Typography>
            <FormControl className={classes.form} variant={'filled'}>
                <Grid container justify={'center'} className={classes.gridContainer}>
                    <Grid item xs={10} className={classes.titleField}>
                        <TextField type={'text'} label={'Title'} className={classes.item}/>
                    </Grid>
                    <Grid item xs={5}>
                        <TextField type={'text'} label={'Price'} className={classes.item}/>
                    </Grid>
                    <Grid item xs={5}>
                        <Select displayEmpty className={classes.item}>
                            <MenuItem selected={false}>
                                per hour
                            </MenuItem>
                            <MenuItem>
                                per day
                            </MenuItem>
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select displayEmpty className={classes.item}>
                            {
                                carBrands.map(brand => {
                                    return (
                                        <MenuItem selected={false}>{brand.brand}</MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select displayEmpty className={classes.item}>
                            {
                                carBrands[0].models.map(model => {
                                    return (
                                        <MenuItem>{model}</MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Button className={classes.item}>
                            Localisation
                            <RoomIcon />
                        </Button>
                    </Grid>
                    <Grid item xs={5}>
                        <Button className={classes.item}>
                            Image
                            <ImageIcon />
                        </Button>
                    </Grid>
                </Grid>
                <Fab variant={'extended'} className={classes.button}>
                    Publish
                    <PublishIcon />
                </Fab>
            </FormControl>
        </Container>
    );
}