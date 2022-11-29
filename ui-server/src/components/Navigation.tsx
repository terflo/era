import React from 'react'
import {AppBar, IconButton, Toolbar, Typography} from "@mui/material";

interface NavigationProps {
    name: string
}

export const Navigation = (props: NavigationProps) => {
    return (
        <AppBar position="static">
            <Toolbar variant="dense">
                <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>

                </IconButton>
                <Typography variant="h6" color="inherit" component="div">
                    {props.name}
                </Typography>
            </Toolbar>
        </AppBar>
    )
}